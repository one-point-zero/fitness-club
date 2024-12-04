package com.example.fitnessclub.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class BookingId implements Serializable {

    @Column(name = "session_id")
    private Long session;

    @Column(name = "client_id")
    private Long client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId that = (BookingId) o;
        return Objects.equals(session, that.session) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, client);
    }

}
