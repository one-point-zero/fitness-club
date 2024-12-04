package com.example.fitnessclub.Repository;

import com.example.fitnessclub.Entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT p FROM Client p WHERE CONCAT(p.ID, p.fullName, p.phoneNumber, p.age, p.membershipType) LIKE %?1%")
    List<Client> search(String keyword);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM fitness_club.booking WHERE booking.client_id = ?1", nativeQuery = true)
    void deleteRelated(Long clientId);

    @Query(value = "select max(p.age) from Client p")
    Integer getMaxAge();

    @Query(value = "select min(p.age) from Client p")
    Integer getMinAge();

    @Query(value = "select p.membershipType from Client p" +
            " group by p.membershipType order by count(p.membershipType) desc limit 1")
    String mostPopularMembershipType();

    @Query(value = "select p.membershipType from Client p" +
            " group by p.membershipType order by count(p.membershipType) asc limit 1")
    String leastPopularMembershipType();
}
