package com.example.fitnessclub.Entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Session {

    private Long ID;
    private String date;
    private String zone;
    private String requiredMembershipType;

    @Id
    public Long getID() {
        return ID;
    }

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Booking> bookings = new ArrayList<>();



    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getRequiredMembershipType() {
        return requiredMembershipType;
    }

    public void setRequiredMembershipType(String requiredMembershipType) {
        this.requiredMembershipType = requiredMembershipType;
    }

}
