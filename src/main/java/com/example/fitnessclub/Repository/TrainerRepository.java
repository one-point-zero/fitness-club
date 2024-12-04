package com.example.fitnessclub.Repository;

import com.example.fitnessclub.Entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query("SELECT p FROM Trainer p WHERE CONCAT(p.ID, p.fullName, p.phoneNumber, p.experience, p.rating) LIKE %?1%")
    List<Trainer> search(String keyword);

    @Query(value = "select max(p.rating) from Trainer p")
    Float getMaxRating();

    @Query(value = "select min(p.rating) from Trainer p")
    Float getMinRating();
}
