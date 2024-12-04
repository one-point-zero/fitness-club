package com.example.fitnessclub.Service;

import com.example.fitnessclub.Entity.Booking;
import com.example.fitnessclub.Entity.BookingDTO;
import com.example.fitnessclub.Entity.BookingId;
import com.example.fitnessclub.Repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepo;


    public List<BookingDTO> getAllBookings(String keyword){
        if (keyword != null){
            return bookingRepo.search(keyword);
        }
        return bookingRepo.fetchBookingsWithDetails();
    }

    public void insertBooking(Long clientId, Long sessionId) {
        bookingRepo.insert(clientId, sessionId);
    }

    public Booking getBookingById(BookingId ID) {
        return bookingRepo.findById(ID).get();
    }

    public void deleteBooking(Long clientId, Long sessionId) {
        bookingRepo.deleteBooking(clientId, sessionId);
    }

}
