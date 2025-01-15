package com.assessment.app.service;

import com.assessment.app.model.Receipt;
import com.assessment.app.response.PointsResponse;

public interface ReceiptProcessorService {

	PointsResponse getPoints(Receipt receipt);

}
