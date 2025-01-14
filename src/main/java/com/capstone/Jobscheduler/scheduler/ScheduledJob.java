package com.capstone.Jobscheduler.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduledJob implements Job{

    public static final Logger logger = LoggerFactory.getLogger(ScheduledJob.class); //org.slfj4

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("Executing scheduled job at {}", context.getFireTime());
    }

}
