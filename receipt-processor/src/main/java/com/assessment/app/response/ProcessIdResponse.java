package com.assessment.app.response;

import lombok.Data;

@Data
public class ProcessIdResponse {
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
