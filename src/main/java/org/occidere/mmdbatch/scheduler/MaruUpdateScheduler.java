package org.occidere.mmdbatch.scheduler;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class MaruUpdateScheduler {
	private JobLauncher jobLauncher;
	private Job job;
	private JobExecution jobExecution;
	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}
	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public JobExecution getJobExecution() {
		return jobExecution;
	}
	public void setJobExecution(JobExecution jobExecution) {
		this.jobExecution = jobExecution;
	}

	public void run() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("id", LocalDateTime.now().toString())
				.toJobParameters();
		jobExecution = jobLauncher.run(job, jobParameters);
		System.out.println(jobExecution.getExitStatus().getExitCode());
	}

}
