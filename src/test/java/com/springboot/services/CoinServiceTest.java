package com.springboot.services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.models.Coin;
import com.springboot.repositories.CoinRepository;

@SpringBootTest
public class CoinServiceTest {
	
	@Autowired
	private CoinRepository coinRepository;
	
	@Autowired
	private CoinService coinService;
	
	
	@Test
	public void GetCoin() {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("id", 1);
		coinService.GetCoin(request);
		
		Assertions.assertTrue(true);
	}

	@Test
	public void InsertCoin() {
		Coin request = new Coin();
		long brforeCount = coinRepository.count();
		coinService.InsertCoin(request);
		long afterCount = coinRepository.count();
		
		Assertions.assertEquals(brforeCount,afterCount+1);
	}

	@Test
	public void UpdateCoin() {
		Coin request = new Coin();
		coinService.UpdateCoin(request);
		
		Assertions.assertTrue(true);
	}

	@Test
	public void DeleteCoin() {
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("id", 1);
		long brforeCount = coinRepository.count();
		coinService.DeleteCoin(request);
		long afterCount = coinRepository.count();
		
		Assertions.assertEquals(brforeCount,afterCount+request.size());
	}

	@Test
	public void CallCoinApi() {
		coinService.CallCoinApi();
		
		Assertions.assertTrue(true);
	}

	@Test
	public void CallCoinApiRefactor() {
		coinService.CallCoinApiRefactor();
		
		Assertions.assertTrue(true);
	}
}
