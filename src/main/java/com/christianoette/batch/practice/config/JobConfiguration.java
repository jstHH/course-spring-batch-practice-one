package com.christianoette.batch.practice.config;

import com.christianoette.batch.practice.model.Person;
import com.christianoette.batch.practice.processor.FilterCustomerProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final JobRepository jobRepository;
    private final ItemReader<Person> personItemReader;
    private final ItemWriter<Person> personItemWriter;
    private final FilterCustomerProcessor filterCustomerProcessor;

    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, JobRepository jobRepository, ItemReader<Person> personItemReader, ItemWriter<Person> personItemWriter, FilterCustomerProcessor filterCustomerProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobRepository = jobRepository;
        this.personItemReader = personItemReader;
        this.personItemWriter = personItemWriter;
        this.filterCustomerProcessor = filterCustomerProcessor;
    }


    @Bean("filterCustomerJob")
    public Job filterCustomerJob() {
        return jobBuilderFactory.get("anonymizeJob")
                .start(step())
                .build();
    }

    @Bean
    public Step step () {
        SimpleStepBuilder<Person, Person> chunk =
                stepBuilderFactory.get("filterCustomer")
                        .repository(jobRepository)
                        .chunk(1);
        return chunk.reader(personItemReader)
                .processor(filterCustomerProcessor)
                .writer(personItemWriter)
                .build();
        }
}
