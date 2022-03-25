package com.BelUsa.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.gson.*;
import com.BelUsa.dataType.*;
import com.BelUsa.managers.FileReaderManager;


public class JsonDataReader {
	//Browser to be used during Parallel Execution
	private final String browserTypePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "BrowserCapability.json";
	private List<BrowserCapabilityData> browserTypeDataList;

	//Credit Card Data
	private final String cardDetailsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "CardDetails.json";
	private List<CardDetailsData> cardDetailsDataList;

	//Member Enrollment Data
	private final String loginCredentialsFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "LoginCredentials.json";
	private List<LoginCredentialsData> loginCredentialsDataList;

	public JsonDataReader() {
		cardDetailsDataList 		= getCardDetailsData();
		browserTypeDataList 		= getBrowserCapabilitesData();
		loginCredentialsDataList 	= getLoginCredentialsData();
	}

	/***************************************************************************************
	 *
	 * Browser Capability Data method
	 *
	 ***************************************************************************************/
	//this method used to read data from json file and store in form of list
	private List<BrowserCapabilityData> getBrowserCapabilitesData() {
		//creating Gson object
		Gson gson = new Gson();

		//create variable to store the json value
		BufferedReader bufferReader = null;
		try {
			//storing value into buffer reader
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + browserTypePath));

			//store value in form of array
			BrowserCapabilityData[] broserCapabilityData = gson.fromJson(bufferReader, BrowserCapabilityData[].class);

			//return list
			return Arrays.asList(broserCapabilityData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + browserTypePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//this method return the all value of the browser capability date which is required
	public BrowserCapabilityData getBrowserCapabilityByBrowserType(String browserType) {
		//accessing browser type
		for (BrowserCapabilityData browserCapabilityData : browserTypeDataList) {

			//if browser type is same as given browser then return its all value
			if (browserCapabilityData.browserType.equalsIgnoreCase(browserType)) {
				return browserCapabilityData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Card Details data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<CardDetailsData> getCardDetailsData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + cardDetailsFilePath));

			//store into form of array
			CardDetailsData[] cardDetailsData = gson.fromJson(bufferReader, CardDetailsData[].class);

			return Arrays.asList(cardDetailsData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + cardDetailsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public CardDetailsData getCardDetailsByRequestType(String cardRequestType) {
		//accessing all data from user preference list
		for (CardDetailsData cardDetailsData : cardDetailsDataList) {
			//checking data
			if (cardDetailsData.requestType.equalsIgnoreCase(cardRequestType)) {
				return cardDetailsData;
			}
		}
		return null;
	}

	/***************************************************************************************
	 *
	 * Login credentials data method
	 *
	 ***************************************************************************************/
	//getting all user preference data
	private List<LoginCredentialsData> getLoginCredentialsData() {

		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			//reading data from json file
			bufferReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + loginCredentialsFilePath));

			//store into form of array
			LoginCredentialsData[] loginCredentialsData = gson.fromJson(bufferReader, LoginCredentialsData[].class);

			return Arrays.asList(loginCredentialsData);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + loginCredentialsFilePath);
		} finally {
			try {
				if (bufferReader != null) bufferReader.close();
			} catch (IOException ignore) {
			}
		}
	}

	//return FSSignUpDate by its first name
	public LoginCredentialsData getCredentialsByUserType(String loginCredentialsUserType) {
		//accessing all data from user preference list
		for (LoginCredentialsData loginCredentialsData : loginCredentialsDataList) {
			//checking data
			if (loginCredentialsData.userType.equalsIgnoreCase(loginCredentialsUserType)) {
				return loginCredentialsData;
			}
		}
		return null;
	}

	/**
	 * Common methods for extracting JsonElements for given key from a JsonElement
	 * @param searchKey Key to search for
	 * @param object Any JsonElement that needs to be decoded
	 * @return List of JsonElements that matches the searchKey
	 */
	public List<JsonElement> jsonExtractor (String searchKey, JsonElement object){
		//list for storing all the returning result
		List<JsonElement> result = new LinkedList<>();

		if (object instanceof JsonArray){
			for (int i = 0; i < object.getAsJsonArray().size(); i++){
				result.addAll(jsonExtractor(searchKey, object.getAsJsonArray().get(i)));
			}
		}else if (object instanceof JsonObject){
			Set<String> listOfKeys = object.getAsJsonObject().keySet();
			for(String key: listOfKeys){
				if (key.equals(searchKey)){
					result.add(object.getAsJsonObject().get(key));
					result.addAll(jsonExtractor(searchKey,object.getAsJsonObject().get(key)));
				}else{
					result.addAll(jsonExtractor(searchKey,object.getAsJsonObject().get(key)));
				}
			}
		}
		return result;
	}
}

