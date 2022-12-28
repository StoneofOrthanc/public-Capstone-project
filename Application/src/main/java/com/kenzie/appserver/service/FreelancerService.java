package com.kenzie.appserver.service;

import com.kenzie.appserver.config.CacheStore;
import com.kenzie.appserver.repositories.FreelancerRepository;
import com.kenzie.appserver.repositories.model.FreelancerRecord;
import com.kenzie.appserver.service.model.Freelancer;
import com.kenzie.capstone.service.client.HireStatusServiceClient;
import com.kenzie.capstone.service.model.HireRequest;
import com.kenzie.capstone.service.model.HireResponse;
import com.kenzie.capstone.service.model.HireStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** This file was created by another team member **/
/** This file was modified by Raymond Morales **/

@Service
public class FreelancerService {
    private FreelancerRepository freelancerRepository;
    private HireStatusServiceClient hireStatusServiceClient;
    private CacheStore cache;

    public FreelancerService(FreelancerRepository repository, HireStatusServiceClient client, CacheStore cache){
        this.freelancerRepository = repository;
        this.hireStatusServiceClient = client;
        this.cache = cache;
    }

    public List<Freelancer> findAll() {
        List<Freelancer> freelancers = new ArrayList<>();

        freelancerRepository
                .findAll()
                .forEach(freelancer -> freelancers.add(toFreelancer(freelancer)));

        return freelancers;
    }

    public Freelancer addNewFreelancer(Freelancer freelancer) {
        if (freelancer == null) {
            throw new IllegalArgumentException();
        }

        FreelancerRecord freelancerRecord = new FreelancerRecord();
        freelancerRecord.setId(freelancer.getId());
        freelancerRecord.setCreatedAt(ZonedDateTime.now());
        freelancerRecord.setContact(freelancer.getContact());
        freelancerRecord.setExpertise(freelancer.getExpertise());
        freelancerRecord.setName(freelancer.getName());
        freelancerRecord.setRate(freelancer.getRate());
        freelancerRecord.setLocation(freelancer.getLocation());
        freelancerRepository.save(freelancerRecord);

        HireRequest hireRequest = new HireRequest();
        hireRequest.setId(freelancerRecord.getId());
        hireRequest.setStatus("Available");
        hireStatusServiceClient.setHireStatus(hireRequest);

        cache.add(freelancer.getId(), freelancer);

        return freelancer;
    }
    
    public Freelancer findById(String id) {

        Freelancer cachedFreelancer = cache.get(id);

        if (cachedFreelancer != null) {
            return cachedFreelancer;

        }

        return freelancerRepository
                .findById(id)
                .map(freelancer -> new Freelancer(freelancer.getId(),
                        freelancer.getName(),
                        freelancer.getExpertise(),
                        freelancer.getRate(),
                        freelancer.getLocation(),
                        freelancer.getContact()))
                .orElse(null);
    }

    public String getFreelancerHireStatus(String freelancerId) {
        HireStatus hireStatus = hireStatusServiceClient.getHireStatus(freelancerId);

        return hireStatus.getStatus();
    }

    public HireStatus updateFreelancerHireStatus(String freelancerId, String status) {
        HireStatus hireStatus = new HireStatus(freelancerId, status);
        HireRequest hireRequest = new HireRequest();
        hireRequest.setId(freelancerId);
        hireRequest.setStatus(status);
        hireStatusServiceClient.setHireStatus(hireRequest);

        return hireStatus;
    }

    //This method was implemented by Raymond Morales to use the new repository
    public void updateFreelancer(Freelancer freelancer) {
        //if the freelancer that is being updated doesn't exist, returns null value
        Optional<FreelancerRecord> optionalFreelancerRecord = freelancerRepository.findById(freelancer.getId());

        if (optionalFreelancerRecord.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Freelancer does not exist.");
        }

        FreelancerRecord record = optionalFreelancerRecord.get();

        if (!record.getName().equals(freelancer.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only the person who created this profile may update it.");
        }
        //return freelancer;
        freelancerRepository.save(toRecord(freelancer));
        cache.evict(freelancer.getId());
        cache.add(freelancerRepository.findById(freelancer.getId()).get().getId(), toFreelancer(freelancerRepository.findById(freelancer.getId()).get()));
    }

    //This conversion helper method was added by Raymond Morales
    private FreelancerRecord toRecord(Freelancer freelancer) {
        FreelancerRecord freelancerRecord = new FreelancerRecord();
        freelancerRecord.setId(freelancer.getId());
        freelancerRecord.setModifiedAt(ZonedDateTime.now());
        freelancerRecord.setContact(freelancer.getContact());
        freelancerRecord.setExpertise(freelancer.getExpertise());
        freelancerRecord.setName(freelancer.getName());
        freelancerRecord.setRate(freelancer.getRate());
        freelancerRecord.setLocation(freelancer.getLocation());
        return freelancerRecord;
    }

    private Freelancer toFreelancer(FreelancerRecord record) {
        return new Freelancer(record.getId(),
                record.getName(),
                record.getExpertise(),
                record.getRate(),
                record.getLocation(),
                record.getContact());
    }

    public void deleteFreelancer(String id){
        Optional<FreelancerRecord> optionalFreelancerRecord = freelancerRepository.findById(id);

        if (optionalFreelancerRecord.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Freelancer does not exist.");
        }

        freelancerRepository.deleteById(id);
        cache.evict(id);
    }
}
