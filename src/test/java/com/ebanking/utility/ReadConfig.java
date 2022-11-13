package com.ebanking.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    private Properties properties;
    public ReadConfig(){

        File src = new File("src/test/resources/properties/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            properties = new Properties();
            properties.load(fis);
        }
        catch (Exception e){
            System.out.println("Unable to load Config Properties File " + e.getMessage());
        }
    }

    public String getDataFromConfig(String key){
        return properties.getProperty(key);
    }

    public String getApplicationURL(){
        return properties.getProperty("baseUrl");
    }

    public String getUserName(){
        return properties.getProperty("username");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }

    public String getBrowser(){
        return properties.getProperty("browser");
    }
}
