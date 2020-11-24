package com.example.Litmus_project.controller;

import com.example.Litmus_project.profile.ProfileModel;
import com.example.Litmus_project.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileModel>> getAllProfiles(){
        List<ProfileModel> profileModels = profileService.getProfiles();
        return new ResponseEntity(profileModels,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileModel> getProfileId(@PathVariable(value = "id") Long id){
        ProfileModel profileModel = profileService.getProfileId(id);
        return new ResponseEntity(profileModel, HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity<ProfileModel> createProfiles(@Valid @RequestBody ProfileModel profileModel) {
        ProfileModel profileModelObj = profileService.createProfile(profileModel);
        return new ResponseEntity(profileModelObj,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProfile(@PathVariable(value = "id") Long id){
        profileService.deleteProfile(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileModel> updateProfile(@PathVariable(value = "id") long id,@Valid @RequestBody ProfileModel profileModel){
        ProfileModel profileModelObj = profileService.updateDomainProfileFromProfile(id, profileModel);
        return new ResponseEntity(profileModelObj,HttpStatus.OK);
    }


}
