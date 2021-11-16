package com.global.testBase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static com.global.driver.WebDriverManager.getChromeDriver;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("=== ScreenshotListener onTestStart ===");
        System.out.println("The name of the testcase is :"+result.getName());  // this returns the test method name!!
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
        System.out.println("The name of the testcase is :"+result.getName());
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);

        String fileName = result.getName();
        fileName = fileName.replace(":","");  // remove colon
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
}
