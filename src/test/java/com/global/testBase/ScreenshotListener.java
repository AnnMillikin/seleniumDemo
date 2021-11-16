package com.global.testBase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.global.testBase.WebDriverManager.getChromeDriver;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("=== ScreenshotListener onTestStart ===");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("=== ScreenshotListener onTestSuccess ===");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("=== ScreenshotListener onTestFailure ===");
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = result.getTestName();  // not giving actual test name TODO
        File targetFile = new File("src/test/screenshots/" + fileName + ".png");
        System.out.println("     ----------- create failure screenshot -------------");
        try {
            FileUtils.copyFile(scrFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        System.out.println("=== ScreenshotListener onTestSkipped ===");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        System.out.println("=== ScreenshotListener onTestFailedButWithinSuccessPercentage ===");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("=== ScreenshotListener onTestFailedWithTimeout ===");
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("=== ScreenshotListener onStart ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("=== ScreenshotListener onFinish ===");
    }
// TODO remove below crap
//    private TakesScreenshot screenshotTaker;  // TODO how is this used?
//
//    public ScreenshotListener() {
//        System.out.println("=== ScreenshotListener constructor ===");
//
//    }
//
//    /*
//Called when an atomic test is about to be started.
//Parameters:
//description - the description of the test that is about to be run (generally a class and method name)
// */
//    @Override
//    public void testStarted(Description description) throws Exception {
//        System.out.println("=== testStarted === ");
//        System.out.println("Starting: " + description.getMethodName().toString());
//    }
///*
//Called when an atomic test has finished, whether the test succeeds or fails.
//Parameters:
//description - the description of the test that just ran
// */
//    @Override
//    public void testFinished(Description description) throws Exception {
//        System.out.println("=== testFinished === ");
//        System.out.println("Finished: " + description.getMethodName().toString());
//    }
//
//    @Override
//    public void testFailure(Failure failure) throws Exception {
//        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);
//        String fileName = UUID.randomUUID().toString() + getClass().getName();
//        File targetFile = new File("src/test/screenshots" + fileName + ".png");
//        FileUtils.copyFile(scrFile, targetFile);
//    }

}
