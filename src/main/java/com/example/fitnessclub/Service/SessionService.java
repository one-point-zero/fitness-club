package com.example.fitnessclub.Service;

import com.example.fitnessclub.Entity.Session;
import com.example.fitnessclub.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    SessionRepository sessionRepo;

    public List<Session> getAllSessions(String keyword) {
        if (keyword != null){
            return sessionRepo.search(keyword);
        }
        return sessionRepo.findAll();
    }

    public void saveSession(Session session) {
        sessionRepo.save(session);
    }

    public Session getSessionById(Long ID) {
        return sessionRepo.findById(ID).get();
    }

    public void deleteSession(Long ID) {
        sessionRepo.deleteById(ID);
    }

    public void deleteRelated(Long ID){
        sessionRepo.deleteRelated(ID);
    }

    public String mostPopularZone(){
        return sessionRepo.mostPopularZone();
    }

    public String leastPopularZone(){
        return sessionRepo.leastPopularZone();
    }

    public String mostPopularMembershipType(){
        return sessionRepo.mostPopularMembershipType();
    }

    public String leastPopularMembershipType(){
        return sessionRepo.leastPopularMembershipType();
    }
}
