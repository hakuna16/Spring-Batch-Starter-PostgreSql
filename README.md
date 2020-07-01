# Spring-Batch-Starter-PostgreSql

## What is Batch Job?
-	A batch job consists of one or more steps. Each step is responsible of completing one logical task. 
-	Every step reads input data, processes the input data, and writes the processed data to the configured output. 
-	If the batch job has more one step, the output of a step is often used as an input of next.

## What is spring batch?
Spring batch is a lightweight framework which is used to develop Batch Applications that are used in Enterprise Applications.

## Spring Batch Architecture
Spring Batch is designed with extensibility and a diverse group of end users in mind. 
The figure below shows the layered architecture that supports the extensibility and ease of use for end-user developers.

![Arch](https://docs.spring.io/spring-batch/docs/current/reference/html/images/spring-batch-layers.png)

## The Domain Language of Batch

To any experienced batch architect, the overall concepts of batch processing used in Spring Batch should be familiar and comfortable. There are "Jobs" and "Steps" and developer-supplied processing units called ItemReader and ItemWriter.

The following diagram is a simplified version of the batch reference architecture

![model](https://docs.spring.io/spring-batch/docs/current/reference/html/images/spring-batch-reference-model.png)

A Job has one to many steps, each of which has exactly one ItemReader, one ItemProcessor, and one ItemWriter. A job needs to be launched (with JobLauncher), and metadata about the currently running process needs to be stored (in JobRepository).

## Job
A Job is an entity that encapsulates an entire batch process.

![job](https://docs.spring.io/spring-batch/docs/current/reference/html/images/job-heirarchy.png)

In Spring Batch, a Job is simply a container for Step instances. It combines multiple steps that belong logically together in a flow and allows for configuration of properties global to all steps, such as restartability. The job configuration contains:

The simple name of the job.

Definition and ordering of Step instances.

Whether or not the job is restartable.

## Step

A Step is a domain object that encapsulates an independent, sequential phase of a batch job. Therefore, every Job is composed entirely of one or more steps. 

![step](https://docs.spring.io/spring-batch/docs/current/reference/html/images/jobHeirarchyWithSteps.png)

## JobRepository
JobRepository is the persistence mechanism for all of the Stereotypes mentioned above. It provides CRUD operations for JobLauncher, Job, and Step implementations. When a Job is first launched, a JobExecution is obtained from the repository, and, during the course of execution, StepExecution and JobExecution implementations are persisted by passing them to the repository.

## JobLauncher
JobLauncher represents a simple interface for launching a Job with a given set of JobParameters, as shown in the following example:

````java
public interface JobLauncher {

public JobExecution run(Job job, JobParameters jobParameters)
            throws JobExecutionAlreadyRunningException, JobRestartException,
                   JobInstanceAlreadyCompleteException, JobParametersInvalidException;
}
````


## Item Reader
ItemReader is an abstraction that represents the retrieval of input for a Step, one item at a time.

## Item Writer
ItemWriter is an abstraction that represents the output of a Step, one batch or chunk of items at a time.

## Item Processor
ItemProcessor is an abstraction that represents the business processing of an item.
