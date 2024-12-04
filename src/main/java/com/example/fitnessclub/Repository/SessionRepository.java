package com.example.fitnessclub.Repository;

import com.example.fitnessclub.Entity.Session;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    @Query("SELECT p FROM Session p WHERE CONCAT(p.ID, p.date, p.zone, p.requiredMembershipType) LIKE %?1%")
    List<Session> search(String keyword);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM fitness_club.booking WHERE booking.session_id = ?1", nativeQuery = true)
    void deleteRelated(Long sessionId);

    @Query(value = "select p.zone from Session p" +
            " group by p.zone order by count(p.zone) desc limit 1")
    String mostPopularZone();

    @Query(value = "select p.zone from Session p" +
            " group by p.zone order by count(p.zone) asc limit 1")
    String leastPopularZone();

    @Query(value = "select p.requiredMembershipType from Session p" +
            " group by p.requiredMembershipType order by count(p.requiredMembershipType) desc limit 1")
    String mostPopularMembershipType();

    @Query(value = "select p.requiredMembershipType from Session p" +
            " group by p.requiredMembershipType order by count(p.requiredMembershipType) asc limit 1")
    String leastPopularMembershipType();
}
