package com.springboot.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.springboot.models.Coin;
import com.springboot.models.response.ResponseClass;

public interface CoinService {
	
	public ResponseEntity<ResponseClass> GetCoin(Map<String, Object> request);
	
	public ResponseEntity<ResponseClass> InsertCoin(Coin request);
	
	public ResponseEntity<ResponseClass> UpdateCoin(Coin request);
	
	public ResponseEntity<ResponseClass> DeleteCoin(Map<String, Object> request);
	
	public ResponseEntity<ResponseClass> CallCoinApi();
	
	public ResponseEntity<ResponseClass> CallCoinApiRefactor();
}
