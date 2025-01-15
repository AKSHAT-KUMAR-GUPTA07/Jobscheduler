package com.capstone.Jobscheduler.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.Jobscheduler.entities.ScheduledJob;


@Repository
public interface ScheduledJobRepository extends JpaRepository<ScheduledJob,Long>{

    Optional<ScheduledJob> findByJobNameAndJobGroup(String JobName , String jobGroup);

    void deleteByJobNameAndJobGroup(String jobName, String jobGroup);

}
