package org.occidere.mmdbatch.batch.job;

import org.occidere.mmdbatch.batch.domain.MangaUpdateInfo;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class MaruProcessor implements ItemProcessor<MangaUpdateInfo, MangaUpdateInfo>, StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public MangaUpdateInfo process(MangaUpdateInfo item) throws Exception {
		// item에 담긴 주소로 크롤링해서 MangaUpdateInfo 도메인에 필요한 데이터 추출 & 객체 생성해 전달
		System.out.println(item);
		return item;
	}

}
