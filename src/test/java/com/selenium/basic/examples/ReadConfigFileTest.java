package com.selenium.basic.examples;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class ReadConfigFileTest {
    @Test
    public void readConfigFileTest() throws IOException {
        // Step 1 Create an object of FileReader or FileInputStream class
        File fileSource = new File("src/test/resources/configfiles/config.properties");
        FileReader fr = new FileReader(fileSource);
        FileInputStream fis = new FileInputStream(fileSource);

        // Step 2. Create object of Properties file
        Properties properties = new Properties();

        // Step 3 Load the file
         properties.load(fr);

        // Step 4 getProperty
        String browser = properties.getProperty("Browser");
        String url = properties.getProperty("URL");

        System.out.println(browser);
        System.out.println(url);

        // Step 4 close file
        fr.close();
        fis.close();

    }

    @Test
    public void writeToConfigFileTest() throws IOException {

        // Step 1 Create an object of FileWriter or FileInputStream class
        FileWriter fw = new FileWriter("src/test/resources/configfiles/config.properties", true);
        FileOutputStream fs = new FileOutputStream("src/test/resources/configfiles/config.properties", true);

        // Step 2. Create object of Properties file
        Properties properties = new Properties();

        // Step 3 Use set property method
        properties.setProperty("Qurl", "eeeeee");
        properties.store(fw,"comments");
        // Step 4 close file
        fw.close();
    }

    @Test
    public void writeToCSVTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)

        // Step 2. Create object of BufferedWriter class

        // Step 3 write data and coma!!!!! for csv file

        // Step 4 close file

    }

    @Test
    public void writeToTXTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)

        // Step 2. Create object of BufferedWriter class

        // Step 3 write data

        // Step 4 close file

    }

    @Test
    public void readFromTXTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)

        // Step 2. Create object of BufferedReader class

        // Step 3 write data

        // Step 4 close file

    }

    @Test
    public void readFromCSVTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)

        // Step 2. Create object of BufferedReader class

        // Step 3 write data


        // Step 4 close file

    }
}
