package com.BelUsa.dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailFileReader {


    private Properties properties;
    private final String EmailFilePath = "/src/main/resources/configs/Email.properties";

    public EmailFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + EmailFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Email.properties not found at " + EmailFilePath);
        }
    }

    public String getOutLookUserName(){
        String outLookUserName = properties.getProperty("outlookUserName");
        if(outLookUserName != null){
            return outLookUserName;
        }else{
            throw new RuntimeException("outlookUserName not specified in the Email.properties file");
        }
    }

    public String getOutLookPassword(){
        String outLookPassword = properties.getProperty("outlookPassword");
        if(outLookPassword != null){
            return outLookPassword;
        }else{
            throw new RuntimeException("outlookPassword not specified in the Email.properties file");
        }
    }

    public String getEmailHost(){
        String emailHost = properties.getProperty("emailHost");
        if(emailHost != null) {
            return emailHost;
        }else {
            throw new RuntimeException("emailHost not specified in the Email.properties file");
        }
    }


    public boolean getEmailAuthentication(){
        String emailAuthentication = properties.getProperty("emailAuthentication");
        if(emailAuthentication != null) {
            return Boolean.valueOf(emailAuthentication);
        }else {
            throw new RuntimeException("emailAuthentication not specified in the Email.properties file");
        }
    }

    public String getEmailPort(){
        String emailPort = properties.getProperty("emailPort");
        if(emailPort != null) {
            return emailPort;
        }else {
            throw new RuntimeException("emailPort not specified in the Configuration.properties file");
        }
    }
}
