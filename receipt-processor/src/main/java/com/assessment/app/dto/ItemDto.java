package com.assessment.app.dto;

import lombok.Data;

@Data
public class ItemDto {
	private String shortDescription;
	private String price;
	
	
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
