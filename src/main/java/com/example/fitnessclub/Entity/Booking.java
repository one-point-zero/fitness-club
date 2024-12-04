package com.example.fitnessclub.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Booking {

    @EmbeddedId
    private BookingId id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("session")
    @JoinColumn(name = "session_id")
    Session session;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @MapsId("client")
    @JoinColumn(name = "client_id")
    Client client;

}
