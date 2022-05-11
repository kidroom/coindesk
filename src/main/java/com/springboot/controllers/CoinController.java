package com.springboot.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.models.Coin;
import com.springboot.models.response.ResponseClass;
import com.springboot.services.CoinService;

@RestController
@RequestMapping(value = "/Coin")
public class CoinController {

	@Autowired
	private CoinService coinService;
	
	@PostMapping(value = "/GetCoin")
	public ResponseEntity<ResponseClass> GetCoin(@RequestBody Map<String, Object> request){
		return coinService.GetCoin(request);
	}
	
	@PostMapping(value = "/InsertCoin")
	public ResponseEntity<ResponseClass> InsertCoin(@RequestBody Coin request){
		return coinService.InsertCoin(request);
	}
	
	@PostMapping(value = "/UpdateCoin")
	public ResponseEntity<ResponseClass> UpdateCoin(@RequestBody Coin request){
		return coinService.UpdateCoin(request);
	}
	
	@PostMapping(value = "/DeleteCoin")
	public ResponseEntity<ResponseClass> DeleteCoin(@RequestBody Map<String, Object> request){
		return coinService.DeleteCoin(request);
	}
	
	@PostMapping(value = "/CallCoinApi")
	public ResponseEntity<ResponseClass> CallCoinApi(){
		return coinService.CallCoinApi();
	}
	
	@PostMapping(value = "/CallCoinApiRefactor")
	public ResponseEntity<ResponseClass> CallCoinApiRefactor(){
		return coinService.CallCoinApiRefactor();
	}
}
