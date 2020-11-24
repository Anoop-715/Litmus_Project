package com.example.Litmus_project.converter;

import com.example.Litmus_project.domain.DomainAddress;
import com.example.Litmus_project.domain.DomainProfile;
import com.example.Litmus_project.profile.Address;
import com.example.Litmus_project.profile.ProfileModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileConverter {

    public List<ProfileModel> getProfilesFromDomainProfiles(List<DomainProfile> domainProfiles) {
        List<ProfileModel> profileModels = new ArrayList<>();
        for (DomainProfile domainProfile : domainProfiles) {
            profileModels.add(getProfileFromDomainProfile(domainProfile));
        }
        return profileModels;
    }

    public DomainProfile createDomainProfileFromProfile(ProfileModel profileModel){

        DomainProfile domainProfile =new DomainProfile();

        domainProfile.setId(profileModel.getId());
        domainProfile.setName(profileModel.getName());
        domainProfile.setEmail(profileModel.getEmail());
        domainProfile.setMobileNo(profileModel.getMobileNo());

        DomainAddress domainAddress = new DomainAddress();

        domainAddress.setLine1(profileModel.getAddress().getLine1());
        domainAddress.setLine2(profileModel.getAddress().getLine2());
        domainAddress.setCity(profileModel.getAddress().getCity());
        domainAddress.setPinCode(profileModel.getAddress().getPinCode());
        domainAddress.setState(profileModel.getAddress().getState());

        domainProfile.setAddress(domainAddress);

        return domainProfile;

    }

    public ProfileModel getProfileFromDomainProfile(DomainProfile domainProfile) {

        ProfileModel profileModelObject = new ProfileModel();

        profileModelObject.setId(domainProfile.getId());
        profileModelObject.setName(domainProfile.getName());
        profileModelObject.setEmail(domainProfile.getEmail());
        profileModelObject.setMobileNo(domainProfile.getMobileNo());

        Address address = new Address();

        address.setLine1(domainProfile.getAddress().getLine1());
        address.setLine2(domainProfile.getAddress().getLine2());
        address.setCity(domainProfile.getAddress().getCity());
        address.setPinCode(domainProfile.getAddress().getPinCode());
        address.setState(domainProfile.getAddress().getState());

        profileModelObject.setAddress(address);

        return profileModelObject;
    }

}
