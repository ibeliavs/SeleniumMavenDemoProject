package ebanking.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.company.example.utility.Helper;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

// Listener class used to generate Extent reports
public class Reporting extends TestListenerAdapter {
    public ExtentReports extent;
    public ExtentTest test;

    @Override
    public void onStart(ITestContext testContext) {
        // crete report
        extent = new ExtentReports();
        //config report settings
        ExtentSparkReporter spark = new ExtentSparkReporter("src/test/resources/reports/Test_Report_" + Helper.getCurrentDateTime()+ ".html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("MyReport");

        extent.attachReporter(spark);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        test = extent.createTest(tr.getName());

        // test.info("I am doing something");
        String screenshotPath = ("src/test/resources/screenshots/" + tr.getName() + ".png");
        File f = new File(screenshotPath);

        if(f.exists()){
            test.fail("Set the failed information" + test.addScreenCaptureFromPath(screenshotPath));
        }
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        //Create new entry in the report
        test = extent.createTest(tr.getName())
                // optional settings
                .assignAuthor("Author")
                .assignCategory("Functional Testing")
                .assignDevice("Windows");
        test.pass("Set the passed information");

    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        test = extent.createTest(tr.getName());
        test.info("Skip test info");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
