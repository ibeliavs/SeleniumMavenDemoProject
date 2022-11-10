package com.company.example.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
    Properties pro;
    public ConfigDataProvider(){

        File src = new File("src/test/resources/configfiles/config.properties");
        try {
            FileInputStream fis = new FileInputStream(src);
            pro = new Properties();
            pro.load(fis);
        }
        catch (Exception e){
            System.out.println("Unable to load Config Properties File " + e.getMessage());
        }
    }

    public String getDataFromConfig(String key){
        return pro.getProperty(key);
    }
    public String getBrowser(){
        return pro.getProperty("Browser");
    }

    public String getURL(){
        return pro.getProperty("qaURL");
    }
}
