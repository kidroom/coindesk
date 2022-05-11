package com.springboot.services.Impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.enums.ResMessage;
import com.springboot.models.Coin;
import com.springboot.models.response.CoinDeskResponse;
import com.springboot.models.response.CoinRefactorResponse;
import com.springboot.models.response.ResponseClass;
import com.springboot.repositories.CoinRepository;
import com.springboot.services.CoinService;

public class CoinServiceImpl implements CoinService {

	@Autowired
	private CoinRepository coinRepository;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public ResponseEntity<ResponseClass> GetCoin(@RequestBody Map<String, Object> request) {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		Coin coins = coinRepository.findById(Integer.parseInt(request.get("id").toString())).get();
		
		responseClass = ResponseClass.getBuilder(resMessage).setData(coins).build();
		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

	@Override
	public ResponseEntity<ResponseClass> InsertCoin(@RequestBody Coin request) {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		Coin coins = coinRepository.save(request);

		responseClass = ResponseClass.getBuilder(resMessage).setData(coins).build();
		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

	@Override
	public ResponseEntity<ResponseClass> UpdateCoin(@RequestBody Coin request) {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		Coin coins = coinRepository.saveAndFlush(request);
		
		responseClass = ResponseClass.getBuilder(resMessage).setData(coins).build();
		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

	@Override
	public ResponseEntity<ResponseClass> DeleteCoin(@RequestBody Map<String, Object> request) {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		coinRepository.deleteById(Integer.parseInt(request.get("id").toString()));
		
		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

	@Override
	public ResponseEntity<ResponseClass> CallCoinApi() {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		//取得資料
		try {
			String returnData = "";
			try {
				URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
				
				// 開啟http連線
				HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
				httpUrlConn.setRequestMethod("Get");
				httpUrlConn.setRequestProperty("Content-Type", "application/json");

				httpUrlConn.setDoOutput(true);
				httpUrlConn.connect();

				// Get the response code
				int statusCode = httpUrlConn.getResponseCode();
				InputStream is = null;

				if (statusCode == 200) {
					is = httpUrlConn.getInputStream();
				} else {
					is = httpUrlConn.getErrorStream();
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				returnData = response.toString();

				// 斷開連線
				httpUrlConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

			CoinDeskResponse coinDeskResponse =  mapper.readValue((String) returnData, CoinDeskResponse.class);
			responseClass = ResponseClass.getBuilder(resMessage).setData(coinDeskResponse).build();
		}catch (Exception e) {
			resMessage = ResMessage.INTERNAL_SERVER_ERROR;
			responseClass = ResponseClass.getBuilder(resMessage).setData(e.toString()).build();
		}

		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

	@Override
	public ResponseEntity<ResponseClass> CallCoinApiRefactor() {
		ResponseClass responseClass = new ResponseClass();
		ResMessage resMessage = ResMessage.OK;
		
		//取得資料
		try {
			String returnData = "";
			try {
				URL url = new URL("https://api.coindesk.com/v1/bpi/currentprice.json");
				
				// 開啟http連線
				HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
				httpUrlConn.setRequestMethod("Get");
				httpUrlConn.setRequestProperty("Content-Type", "application/json");

				httpUrlConn.setDoOutput(true);
				httpUrlConn.connect();

				// Get the response code
				int statusCode = httpUrlConn.getResponseCode();
				InputStream is = null;

				if (statusCode == 200) {
					is = httpUrlConn.getInputStream();
				} else {
					is = httpUrlConn.getErrorStream();
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				returnData = response.toString();

				// 斷開連線
				httpUrlConn.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}

			CoinDeskResponse coinDeskResponse =  mapper.readValue((String) returnData, CoinDeskResponse.class);
			
			List<CoinRefactorResponse> coinRefactorResponses = new ArrayList<CoinRefactorResponse>();
			
			
			CoinRefactorResponse usdResponse = new CoinRefactorResponse();
			usdResponse.setCode(coinDeskResponse.getBpi().getUSD().getCode());
			usdResponse.setName("美金");
			usdResponse.setDescription(coinDeskResponse.getBpi().getUSD().getDescription());
			usdResponse.setRate(coinDeskResponse.getBpi().getUSD().getRate());
			usdResponse.setRate_float(coinDeskResponse.getBpi().getUSD().getRate_float());
			usdResponse.setSymbol(coinDeskResponse.getBpi().getUSD().getSymbol());
			usdResponse.setUpdate_time(coinDeskResponse.getTime().getUpdated());
			coinRefactorResponses.add(usdResponse);
			
			CoinRefactorResponse eurResponse = new CoinRefactorResponse();
			eurResponse.setCode(coinDeskResponse.getBpi().getEUR().getCode());
			eurResponse.setName("歐元");
			eurResponse.setDescription(coinDeskResponse.getBpi().getEUR().getDescription());
			eurResponse.setRate(coinDeskResponse.getBpi().getEUR().getRate());
			eurResponse.setRate_float(coinDeskResponse.getBpi().getEUR().getRate_float());
			eurResponse.setSymbol(coinDeskResponse.getBpi().getEUR().getSymbol());
			eurResponse.setUpdate_time(coinDeskResponse.getTime().getUpdated());
			coinRefactorResponses.add(eurResponse);
			
			CoinRefactorResponse gbpResponse = new CoinRefactorResponse();
			gbpResponse.setCode(coinDeskResponse.getBpi().getGBP().getCode());
			gbpResponse.setName("英鎊");
			gbpResponse.setDescription(coinDeskResponse.getBpi().getGBP().getDescription());
			gbpResponse.setRate(coinDeskResponse.getBpi().getGBP().getRate());
			gbpResponse.setRate_float(coinDeskResponse.getBpi().getGBP().getRate_float());
			gbpResponse.setSymbol(coinDeskResponse.getBpi().getGBP().getSymbol());
			gbpResponse.setUpdate_time(coinDeskResponse.getTime().getUpdated());
			coinRefactorResponses.add(gbpResponse);
			
			responseClass = ResponseClass.getBuilder(resMessage).setData(gbpResponse).build();
		}catch (Exception e) {
			resMessage = ResMessage.INTERNAL_SERVER_ERROR;
			responseClass = ResponseClass.getBuilder(resMessage).setData(e.toString()).build();
		}

		return ResponseEntity.status(resMessage.getCode()).body(responseClass);
	}

}
