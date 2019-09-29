package com.v5ent.movie.entity.vo;

import org.hibernate.validator.constraints.NotBlank;

public class UrlVo {
	@NotBlank
	private String url;
	@NotBlank
	private String id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
