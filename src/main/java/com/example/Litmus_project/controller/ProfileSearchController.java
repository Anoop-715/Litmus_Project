package com.example.Litmus_project.controller;

import com.example.Litmus_project.profile.ProfileModel;
import com.example.Litmus_project.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile/search")
public class ProfileSearchController {

    private final ProfileService profileService;

    public ProfileSearchController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProfileModel>> searchWithName(@PathVariable(value = "name") String name){
        List<ProfileModel> profileModels = profileService.searchProfileByName(name);
        return new ResponseEntity(profileModels, HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ProfileModel> searchWithEmail(@PathVariable(value = "email") String email){
        ProfileModel profileModels = profileService.searchProfileByEmail(email);
        return new ResponseEntity(profileModels, HttpStatus.OK);
    }

    @GetMapping("/mobile/{mobileNo}")
    public ResponseEntity<List<ProfileModel>> searchWithMobileNo(@PathVariable(value = "mobileNo") Long mobileNo){
        List<ProfileModel> profileModels = profileService.searchWithMobileNo(mobileNo);
        return new ResponseEntity(profileModels, HttpStatus.OK);
    }

}
