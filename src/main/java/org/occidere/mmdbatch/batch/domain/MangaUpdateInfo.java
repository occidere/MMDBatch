package org.occidere.mmdbatch.batch.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MangaUpdateInfo {
	private static final String URL_PREFIX = "https://marumaru.in";
	private String url; // mangaup 을 포함한 주소
	private String thumbnail; // 섬네일 이미지 주소
	private String title; // 만화 제목
	private int comment; // 댓글 수
	private String date; // 업로드 날짜 (yyyy-MM-dd)

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		/* /b/mangaup/302886 -> https://marumaru.in/b/mangaup/302886 */
		this.url = String.format("%s%s", URL_PREFIX, url.trim());
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = String.format("%s%s", URL_PREFIX, thumbnail.trim());
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date.trim();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MangaUpdateInfo == false) {
			return false;
		}
		MangaUpdateInfo other = (MangaUpdateInfo) obj;

		return (comment == other.comment) && date.equals(other.date) && thumbnail.equals(other.thumbnail)
				&& url.equals(other.url) && title.equals(other.title);
	}

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
