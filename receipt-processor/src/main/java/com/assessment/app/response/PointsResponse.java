package com.assessment.app.response;

import lombok.Data;

@Data
public class PointsResponse {
	String points;

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
}
