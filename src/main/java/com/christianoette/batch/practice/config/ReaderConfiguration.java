package com.christianoette.batch.practice.config;

import com.christianoette.batch.dontchangeit.utils.CourseUtils;
import com.christianoette.batch.practice.model.Person;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfiguration {

    private final StepBuilderFactory stepBuilderFactory;

    public ReaderConfiguration(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("personItemReader")
    @StepScope
    public ItemStreamReader<Person> personItemReader (@Value("#{jobParameters['inputPath']}") String inputPath) {
        Resource inputFile = CourseUtils.getFileResource(inputPath);

        return new JsonItemReaderBuilder<Person>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Person.class))
                .resource(inputFile)
                .name("personItemReader")
                .build();
    }
}
