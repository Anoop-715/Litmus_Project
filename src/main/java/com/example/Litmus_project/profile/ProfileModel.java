package com.example.Litmus_project.profile;

import javax.validation.constraints.*;


public class ProfileModel {

    private Long id;
    @NotBlank(message="Name cannot be missing or empty")
    private String name;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email Synthetical Error")
    private String email;
    @NotNull
    private Long mobileNo;
    private Address address;

    public ProfileModel(Long id, String name, String email, Long mobileNo, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobileNo = mobileNo;
        this.address = address;
    }

    public ProfileModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo=" + mobileNo +
                ", address=" + address +
                '}';
    }
}
