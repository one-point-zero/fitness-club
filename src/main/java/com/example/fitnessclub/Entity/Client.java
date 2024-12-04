package com.example.fitnessclub.Entity;


import jakarta.persistence.*;

import java.util.*;

@Entity
public class Client {

    private Long ID;
    private String fullName;
    private String phoneNumber;
    private Integer age;
    private String membershipType;
    private String membershipDaysExpiry;

    @Id
    public Long getID() {
        return ID;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Booking> bookings = new ArrayList<>();

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipDaysExpiry() {
        return membershipDaysExpiry;
    }

    public void setMembershipDaysExpiry(String membershipDaysExpiry) {
        this.membershipDaysExpiry = membershipDaysExpiry;
    }


}
