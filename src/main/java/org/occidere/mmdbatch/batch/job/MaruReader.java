package org.occidere.mmdbatch.batch.job;

import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.occidere.mmdbatch.batch.domain.MangaUpdateInfo;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MaruReader implements ItemReader<MangaUpdateInfo>, StepExecutionListener {
	private String url;
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	private Iterator<MangaUpdateInfo> mangaupIter;
	
	@Override
	public void beforeStep(StepExecution stepExecution) {
		mangaupIter = getMangaupIter();
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return null;
	}

	@Override
	public MangaUpdateInfo read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// 만화 주소들 1개씩 넘김 
		return mangaupIter.hasNext() ? mangaupIter.next() : null;
	}
	
	/* Jsoup 파싱해서 MangaUpdateInfo 담은 이터레이터 반환 */
	private Iterator<MangaUpdateInfo> getMangaupIter() {
		Iterator<MangaUpdateInfo> mangaupIter = Collections.emptyIterator();
		
		try {
			Document document = Jsoup.connect(url)
					.userAgent("Mozilla/5.0")
					.followRedirects(true)
					.timeout(30_000)
					.get();
			
			Elements widgetGallery01Elements = document.getElementsByClass("widget_gallery01"); // marumaru.in 첫 화면에서 각 게시판 미리보기 박스들 
			Element mangaupElement = widgetGallery01Elements.first(); // 첫번째가 만화 업데이트 알림 부분 
			
			mangaupIter = mangaupElement.getElementsByTag("li").stream()
				.map(this::parse)
				.collect(Collectors.toList())
				.iterator();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mangaupIter;
	}
	
	/* html 라인 파싱해서 MangaUpdateInfo 객체로 매핑 */
	private MangaUpdateInfo parse(Element element) {
		Elements ahref = element.select("a[href]"); // 2개가 나옴 

		String url = ahref.attr("href");
		String title = ahref.select("a[title]").text().trim();
		String thumbnail = ahref.select("img[src]").attr("src").trim();
		String date = element.select(".date").text().replaceAll("[^(\\d-\\d-\\d)]", ""); // yyyy-MM-dd 부분만 남김 (new 라는 텍스트 삭제)
		
		String commentStr = element.getElementsByClass("comment").text().replaceAll("\\D", ""); // [, ], new 라는 텍스트 삭제 
		int comment = Integer.parseInt(StringUtils.isNotEmpty(commentStr) ? commentStr : "0"); // null or "" -> "0"
		
		MangaUpdateInfo mangaUpdateInfo = new MangaUpdateInfo() {{
			setUrl(url);
			setTitle(title);
			setThumbnail(thumbnail);
			setDate(date);
			setComment(comment);
		}};
		
		return mangaUpdateInfo;
	}
}
