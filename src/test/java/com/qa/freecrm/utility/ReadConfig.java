package com.qa.freecrm.utility;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    private Properties properties;
    public ReadConfig(){
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/qa/freecrm/config/config.properties");
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
        return properties.getProperty("url");
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
