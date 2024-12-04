package com.example.fitnessclub.Service;


import com.example.fitnessclub.Entity.Trainer;
import com.example.fitnessclub.Repository.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepo;

    public List<Trainer> getAllTrainers(String keyword) {
        if (keyword != null){
            return trainerRepo.search(keyword);
        }
        return trainerRepo.findAll();
    }

    @Transactional
    public void saveTrainer(Trainer trainer) {
        trainerRepo.flush();
        trainerRepo.save(trainer);
    }

    public Trainer getTrainerById(Long ID) {
        return trainerRepo.findById(ID).get();
    }

    public void deleteTrainer(Long ID) {
        trainerRepo.deleteById(ID);
    }

    public Float getMaxRating(){
        return trainerRepo.getMaxRating();
    }

    public Float getMinRating(){
        return trainerRepo.getMinRating();
    }
}
