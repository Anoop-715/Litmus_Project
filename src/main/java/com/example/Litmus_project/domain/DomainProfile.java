package com.example.Litmus_project.domain;

import javax.persistence.*;

@Entity
@Table(name = "domainProfiles")
public class DomainProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "Mobile_no")
    private Long mobileNo;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id")
    private DomainAddress domainAddress;

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

    public DomainAddress getAddress() {
        return domainAddress;
    }

    public void setAddress(DomainAddress domainAddress) {
        this.domainAddress = domainAddress;
    }
}
