package com.BelUsa.managers;

import com.BelUsa.dataProviders.ConfigFileReader;
import com.BelUsa.dataProviders.EmailFileReader;
import com.BelUsa.dataProviders.JsonDataReader;

public class FileReaderManager {
	
	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static ConfigFileReader  configFileReader;
	private static JsonDataReader    jsonDataReader;
	private static EmailFileReader   emailFileReader;

	private FileReaderManager() {
		
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}
	
	public ConfigFileReader getConfigReader() {
		if(configFileReader == null) {
			return configFileReader = new ConfigFileReader();
		}else {
			return configFileReader;
		}
	}

	public EmailFileReader getEmailReader(){
		if(emailFileReader == null) {
			return emailFileReader = new EmailFileReader();
		}else {
			return emailFileReader;
		}
	}
	
	 public JsonDataReader getJsonReader(){
		if(jsonDataReader == null) {
			return jsonDataReader = new JsonDataReader();
		}else {
			return jsonDataReader;
		}
	}


}
