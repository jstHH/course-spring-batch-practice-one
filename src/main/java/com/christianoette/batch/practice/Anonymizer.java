package com.christianoette.batch.practice;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class Anonymizer {

    private final JobLauncher jobLauncher;
    private final Job filterCustomerJob;

    public Anonymizer(JobLauncher jobLauncher, Job job, Job filterCustomerJob) {
        this.jobLauncher = jobLauncher;

        this.filterCustomerJob = filterCustomerJob;
    }

    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addParameter("inputPath", new JobParameter("classpath:testData/persons.json"))
                .addParameter("outputPath", new JobParameter("public/upload/customer.json"))
                .toJobParameters();

        jobLauncher.run(filterCustomerJob, jobParameters);
    }
}
