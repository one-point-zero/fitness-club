package com.example.fitnessclub.Service;


import com.example.fitnessclub.Entity.Client;
import com.example.fitnessclub.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepo;

    public List<Client> getAllClients(String keyword) {
        if (keyword != null){
            return clientRepo.search(keyword);
        }
        return clientRepo.findAll();
    }

    public void saveClient(Client client) {
        clientRepo.save(client);
    }

    public Client getClientById(Long ID) {
        return clientRepo.findById(ID).get();
    }

    public void deleteClient(Long ID) {
        clientRepo.deleteById(ID);
    }

    public void deleteRelated(Long ID) {
        clientRepo.deleteRelated(ID);
    }

    public Integer getMaxAge(){
        return clientRepo.getMaxAge();
    }

    public Integer getMinAge(){
        return clientRepo.getMinAge();
    }

    public String mostPopularMembershipType(){
        return clientRepo.mostPopularMembershipType();
    }

    public String leastPopularMembershipType(){
        return clientRepo.leastPopularMembershipType();
    }

}
