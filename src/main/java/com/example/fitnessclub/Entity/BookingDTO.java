package com.example.fitnessclub.Entity;

public class BookingDTO {
    private String clientName;
    private String bookingDate;
    private String bookingZone;
    private BookingId id;

    public BookingDTO(BookingId id, String clientName, String bookingDate, String bookingZone) {
        this.clientName = clientName;
        this.bookingDate = bookingDate;
        this.bookingZone = bookingZone;
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingZone() {
        return bookingZone;
    }

    public void setBookingZone(String bookingZone) {
        this.bookingZone = bookingZone;
    }

    public BookingId getId() {
        return id;
    }

    public void setId(BookingId id) {
        this.id = id;
    }
}
