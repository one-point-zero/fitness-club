package com.example.fitnessclub.Repository;

import com.example.fitnessclub.Entity.Booking;
import com.example.fitnessclub.Entity.BookingDTO;
import com.example.fitnessclub.Entity.BookingId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, BookingId>  {
    @Query("SELECT new com.example.fitnessclub.Entity.BookingDTO(b.id, c.fullName, s.date, s.zone) FROM Booking b JOIN b.session s JOIN b.client c " +
            "WHERE CONCAT(b.client.fullName, b.session.date, b.session.zone) LIKE %?1%")
    List<BookingDTO> search(String keyword);

    @Query("SELECT new com.example.fitnessclub.Entity.BookingDTO(b.id, c.fullName, s.date, s.zone) " +
            "FROM Booking b " +
            "JOIN b.session s " +
            "JOIN b.client c")
    List<BookingDTO> fetchBookingsWithDetails();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO fitness_club.booking values(?1,?2)", nativeQuery = true)
    void insert(Long clientId, Long sessionId);

    @Transactional
    @Modifying
    @Query(value = "delete from fitness_club.booking where client_id = ?1 and session_id = ?2", nativeQuery = true)
    void deleteBooking(Long clientId, Long sessionId);
}
