package com.example.Litmus_project.domain;

import javax.persistence.*;

@Entity
@Table(name = "domainAddress")
public class DomainAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Line1")
    private String line1;
    @Column(name = "Line2")
    private String line2;
    @Column(name = "City")
    private String city;
    @Column(name = "State")
    private String state;
    @Column(name = "PinCode")
    private Long pinCode;

    public DomainAddress() {
    }

    public DomainAddress(String line1, String line2, String city, String state, Long pinCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getPinCode() {
        return pinCode;
    }

    public void setPinCode(Long pinCode) {
        this.pinCode = pinCode;
    }
}
