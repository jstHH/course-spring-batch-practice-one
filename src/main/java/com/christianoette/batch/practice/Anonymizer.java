package com.christianoette.batch.practice;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class Anonymizer {

    private final JobLauncher jobLauncher;
    private final Job job;

    public Anonymizer(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void runJob () throws Exception {
        jobLauncher.run(job, new JobParameters());
    }
}
