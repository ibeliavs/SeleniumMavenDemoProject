package ui;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("TestCase start and Test details are " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("TestCase success and Test details are " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("TestCase failed and Test details are " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("TestCase skipped and Test details are " + result.getName());
    }
}
