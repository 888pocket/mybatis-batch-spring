package org.example.batch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.mapper.HelloWorldMapper;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class HelloWorldJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private final HelloWorldMapper helloWorldMapper;

    @Bean
    public Job sampleJob() {
        return jobBuilderFactory.get("Helloworld")
            .incrementer(new RunIdIncrementer())
            .start(helloworldStep())
            .build();
    }

    @Bean
    @JobScope
    public Step helloworldStep() {
        return stepBuilderFactory.get("HelloworldStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("Hello World!!");
                log.info("Select from dual: {}", helloWorldMapper.find1FromDual());
                return RepeatStatus.FINISHED;
            })
            .build();
    }

}
