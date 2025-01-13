package com.capstone.Jobscheduler.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.Jobscheduler.entities.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long>{

}
