package com.assessment.app.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.app.dto.ReceiptDto;
import com.assessment.app.model.Receipt;
import com.assessment.app.response.PointsResponse;
import com.assessment.app.response.ProcessIdResponse;
import com.assessment.app.service.ReceiptProcessorService;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {
	
	@Autowired
	private ReceiptProcessorService receiptProcessorService;
	
	private Map<String, Receipt> receiptMap = new HashMap<>();
	
	@PostMapping("/process")
	public ResponseEntity<ProcessIdResponse> getProcessId(@RequestBody ReceiptDto receiptDto) {
		Receipt receipt = new Receipt();
		receipt.setId(UUID.randomUUID().toString());
		receipt.setRetailer(receiptDto.getRetailer());
		receipt.setPurchaseDate(receiptDto.getPurchaseDate());
		receipt.setPurchaseTime(receiptDto.getPurchaseTime());
		receipt.setTotal(receiptDto.getTotal());
		receipt.setItems(receiptDto.getItems());
		
		receiptMap.put(receipt.getId(), receipt);
		
		ProcessIdResponse response = new ProcessIdResponse();
		response.setId(receipt.getId());
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{id}/points")
	public ResponseEntity<PointsResponse> getPoints(@PathVariable String id) {
		Receipt receipt = receiptMap.get(id);
		
		if (receipt == null) {
			return ResponseEntity.notFound().build();
		}
		
		PointsResponse response = receiptProcessorService.getPoints(receipt);
		
		return ResponseEntity.ok(response);
	}
}