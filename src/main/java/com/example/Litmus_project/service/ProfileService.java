package com.example.Litmus_project.service;

import com.example.Litmus_project.converter.ProfileConverter;
import com.example.Litmus_project.customException.DuplicateEmailException;
import com.example.Litmus_project.customException.ProfileNotFoundException;
import com.example.Litmus_project.domain.DomainProfile;
import com.example.Litmus_project.profile.ProfileModel;
import com.example.Litmus_project.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Litmus_project.util.ErrorMessage.DUPLICATE_EMAIL_FOUND;
import static com.example.Litmus_project.util.ErrorMessage.PROFILE_NOT_FOUND;

@Service
public class ProfileService {

    private Logger logger = LoggerFactory.getLogger(ProfileService.class);

    private ProfileRepository profileRepository;
    private ProfileConverter profileConverter;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, ProfileConverter profileConverter) {
        this.profileRepository = profileRepository;
        this.profileConverter = profileConverter;
    }

    public ProfileModel getProfileId(Long id){
        DomainProfile domainProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(PROFILE_NOT_FOUND));
        return profileConverter.getProfileFromDomainProfile(domainProfile);
    }

    public List<ProfileModel> getProfiles() {
            List<DomainProfile> domainProfiles = profileRepository.findAll();
            return profileConverter.getProfilesFromDomainProfiles(domainProfiles);
    }

    public ProfileModel createProfile(ProfileModel profileModel) {
        try {
            DomainProfile domainProfile = profileConverter.createDomainProfileFromProfile(profileModel);
            DomainProfile savedDomainProfile = profileRepository.saveAndFlush(domainProfile);
            return profileConverter.getProfileFromDomainProfile(savedDomainProfile);
        } catch(DataIntegrityViolationException exc) {
            logger.error(exc.getMessage(), exc);
            throw new DuplicateEmailException(DUPLICATE_EMAIL_FOUND);
        }
    }

    public ProfileModel updateDomainProfileFromProfile(long id,ProfileModel profileModel){
        try {
            profileRepository.findById(id)
                    .orElseThrow(() -> new ProfileNotFoundException(PROFILE_NOT_FOUND));
            profileModel.setId(id);
            DomainProfile updatedDomainProfile = profileConverter.createDomainProfileFromProfile(profileModel);
            return profileConverter.getProfileFromDomainProfile(profileRepository.save(updatedDomainProfile));
        } catch (DataIntegrityViolationException exc){
            logger.error(exc.getMessage(), exc);
            throw new DuplicateEmailException(DUPLICATE_EMAIL_FOUND);
        }
    }

    public List<ProfileModel> searchProfileByName(String name){
            List<DomainProfile> domainProfiles = profileRepository.findByName(name)
                    .orElseThrow(() ->new ProfileNotFoundException(PROFILE_NOT_FOUND));
            return profileConverter.getProfilesFromDomainProfiles(domainProfiles);
    }

    public ProfileModel searchProfileByEmail(String email) {
            DomainProfile domainProfile = profileRepository.findByEmail(email)
                    .orElseThrow(() ->new ProfileNotFoundException(PROFILE_NOT_FOUND));
            return profileConverter.getProfileFromDomainProfile(domainProfile);
    }

    public List<ProfileModel> searchWithMobileNo(Long mobileNo){
            List<DomainProfile> domainProfiles = profileRepository.findByMobileNo(mobileNo)
                    .orElseThrow(() ->new ProfileNotFoundException(PROFILE_NOT_FOUND));
            return profileConverter.getProfilesFromDomainProfiles(domainProfiles);
    }

    public void deleteProfile(Long id){
        try {
            profileRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw  new ProfileNotFoundException(PROFILE_NOT_FOUND);
        }
    }
}
