package org.occidere.mmdbatch.batch.job;

import java.util.List;

import org.occidere.mmdbatch.batch.domain.MangaUpdateInfo;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

public class MaruWriter implements ItemWriter<MangaUpdateInfo>, StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// DB open
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		//DB close
		return null;
	}

	@Override
	public void write(List<? extends MangaUpdateInfo> items) throws Exception {
		//DB Write
		System.out.println("write()");
	}

}
