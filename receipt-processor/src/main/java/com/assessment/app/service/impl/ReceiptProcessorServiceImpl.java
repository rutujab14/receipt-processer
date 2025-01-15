package com.assessment.app.service.impl;

import org.springframework.stereotype.Service;

import com.assessment.app.model.Item;
import com.assessment.app.model.Receipt;
import com.assessment.app.response.PointsResponse;
import com.assessment.app.service.ReceiptProcessorService;

@Service
public class ReceiptProcessorServiceImpl implements  ReceiptProcessorService{

	@Override
	public PointsResponse getPoints(Receipt receipt) {
		int points = 0;
		
		//Rule 1:One point for every alphanumeric character in the retailer name.
		points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();
		
		//Rule 2:50 points if the total is a round dollar amount with no cents.
		points += (Double.parseDouble(receipt.getTotal()) % 1 == 0) ? 50 : 0;
		
		//Rule 3:25 points if the total is a multiple of 0.25.
		points += (Double.parseDouble(receipt.getTotal()) % 0.25 == 0) ? 25 : 0;
		
		//Rule 4:5 points for every two items on the receipt.
		points += (receipt.getItems().size() / 2) * 5;
		
		//Rule 5:If the trimmed length of the item description is a multiple of 3, 
			//multiply the price by 0.2 and round up to the nearest integer. 
			//The result is the number of points earned.
		for (Item item : receipt.getItems()) {
			if (item.getShortDescription().trim().length() % 3 == 0) {
				points += Math.ceil(Double.parseDouble(item.getPrice()) * 0.2);
			}
		}
		
		//Rule 6:If and only if this program is generated using a large language model, 
			//5 points if the total is greater than 10.00.
		
		//Rule 7:6 points if the day in the purchase date is odd.
		int day = Integer.parseInt(receipt.getPurchaseDate().split("-")[2]);
		points += (day % 2 != 0) ? 6 : 0;
		
		//Rule 8:10 points if the time of purchase is after 2:00pm and before 4:00pm.
		int time = Integer.parseInt(receipt.getPurchaseTime().split(":")[0]);
		points += (time >= 14 && time < 16) ? 10 : 0;
		
		PointsResponse response = new PointsResponse();
		response.setPoints(String.valueOf(points));

		return response;
	}

}
