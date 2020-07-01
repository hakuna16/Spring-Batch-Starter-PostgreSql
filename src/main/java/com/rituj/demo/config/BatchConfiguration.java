package com.rituj.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rituj.demo.processor.CustomProcessor;
import com.rituj.demo.reader.CustomReader;
import com.rituj.demo.writer.CustomWriter;

import lombok.AllArgsConstructor;

/**
 * This class is used to do the batch configuration.
 */

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    /**
     * This method creates an {@link ItemReader} bean of type
     * {@link CustomReader}.
     * 
     * @see {@link CustomReader}.
     * 
     * @return bean instance of {@link CustomReader}.
     */
    @Bean
    public ItemReader<String> eventReader() {
        return new CustomReader();
    }

    /**
     * This method creates an {@link ItemProcessor} bean of type
     * {@link CustomProcessor}.
     * 
     * @see {@link CustomProcessor}.
     * 
     * @return bean instance of {@link CustomProcessor}.
     */
    @Bean
    public ItemProcessor<String, String> processRead() {
        return new CustomProcessor();
    }

    /**
     * This method creates an {@link ItemWriter} bean of type
     * {@link CustomProcessor}.
     * 
     * @see {@link CustomWriter}.
     * 
     * @return bean instance of {@link CustomWriter}.
     */
    @Bean
    public ItemWriter<String> eventWriter() {
        return new CustomWriter();
    }

    /**
     * This method creates an {@link Step} bean using {@link StepBuilderFactory}.
     * {@link StepBuilderFactory#get(String)} will creates a job name.
     * 
     * This method uses {@link StepBuilderFactory} to create a {@link Step}. 
     * The step has reader as {@link BatchConfiguration#eventReader()},
     * processor as {@link BatchConfiguration#processRead()}
     * and writer as {@link BatchConfiguration#eventWriter()}.
     * 
     * @return bean instance of {@link Step} to process.
     */
    @Bean
    public Step read() {
        return stepBuilderFactory.get("read").<String, String>chunk(1).reader(eventReader()).processor(processRead())
                .writer(eventWriter()).build();
    }

    /**
     * This method creates an {@link Job} bean using {@link JobBuilderFactory}.
     * 
     * {@link JobBuilderFactory#get(String)} will create a job name.
     * and then starts the job using {@link BatchConfiguration#read()}
     * 
     * @return bean instance of {@link Job} to process.
     */
    @Bean
    public Job mainJob() {
        return jobBuilderFactory.get("mainJob").incrementer(new RunIdIncrementer()).listener(new JobListener())
                .start(read()).build();
    }
}
