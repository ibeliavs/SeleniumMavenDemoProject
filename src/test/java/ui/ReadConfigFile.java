package ui;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Properties;

public class ReadConfigFile {
    @Test
    public void readConfigFileTest() throws IOException {
        // Current working directory can be used in file path
        System.out.println(System.getProperty("user,dir"));

        // Step 1 Create an object of FileReader or FileInputStream class
        //FileInputStream file = new FileInputStream("src/test/resources/configfiles/driverconfig.properties");
        FileReader file = new FileReader("src/test/resources/configfiles/driverconfig.properties");

        // Step 2. Create object of Properties file
        Properties properties = new Properties();

        // Step 3 Load the file
        properties.load(file);

        // Step 4 getProperty
        String url = properties.getProperty("URL");
        String browser = properties.getProperty("browser");
        String driverPath = properties.getProperty("ChromeDriver");

    }

    @Test
    public void writeToConfigFileTest() throws IOException {

        // Step 1 Create an object of FileWriter or FileInputStream class
        //FileOutputStream file = new FileOutputStream("src/test/resources/configfiles/writeconfigNew.properties");
        FileWriter file = new FileWriter("src/test/resources/configfiles/writeconfig.properties", true);

        // Step 2. Create object of Properties file
        Properties properties = new Properties();

        // Step 3 Use set property method
        properties.setProperty("url", "http://freecrm.com/");
        properties.setProperty("browser", "chrome");
        properties.store(file, "Update file");
    }

    @Test
    public void writeToCSVTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)
        FileWriter file = new FileWriter("src/test/resources/configfiles/csvdata.csv", true);

        // Step 2. Create object of BufferedWriter class
        BufferedWriter bw = new BufferedWriter(file);

        // Step 3 write data and coma!!!!! for csv file
       for(int i = 0; i<5; i++)
           bw.write("This is test string" + ",");

        // Step 4 close file
        bw.close();
    }

    @Test
    public void writeToTXTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)
        FileWriter file = new FileWriter("src/test/resources/configfiles/textdata.txt", true);

        // Step 2. Create object of BufferedWriter class
        BufferedWriter bw = new BufferedWriter(file);

        // Step 3 write data
        bw.write("This is test string.");

        // Step 4 close file
        bw.close();
    }

    @Test
    public void readFromTXTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)
        FileReader file = new FileReader("src/test/resources/configfiles/testdata.txt");

        // Step 2. Create object of BufferedReader class
        BufferedReader br = new BufferedReader(file);

        // Step 3 write data
        String str = br.readLine();
        System.out.println(str);

        // Step 4 close file
        br.close();
    }

    @Test
    public void readFromCSVTest() throws IOException {

        // Step 1 Create an object of FileWriter (true for append text)
        FileReader file = new FileReader("src/test/resources/configfiles/csvdata.csv");

        // Step 2. Create object of BufferedReader class
        BufferedReader br = new BufferedReader(file);

        // Step 3 write data
        String str = br.readLine();
        System.out.println(str);

        // Step 4 close file
        br.close();
    }
}
