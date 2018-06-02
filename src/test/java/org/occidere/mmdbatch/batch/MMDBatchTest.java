package org.occidere.mmdbatch.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:application-batch.xml",
		"classpath:application-batch-context.xml",
		"classpath:application-batch-test.xml"})
public class MMDBatchTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void launchJob() throws Exception {
		// testing a job
		// JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("mangaUpdateStep");

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}
}
