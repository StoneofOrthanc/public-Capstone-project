package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.FreelancerRecord;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/** This file was created by another team member **/

@EnableScan
public interface FreelancerRepository extends CrudRepository<FreelancerRecord, String> {
    List<FreelancerRecord> findAll();
    Optional<FreelancerRecord> findById(String id);
    void deleteById(String id);
}
