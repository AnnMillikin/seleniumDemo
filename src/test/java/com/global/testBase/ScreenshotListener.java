package com.global.testBase;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

import static com.global.driver.WebDriverManager.getChromeDriver;

public class ScreenshotListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        Reporter.log("\n=== Test Start: "+result.getName()+ " ===", true); // this returns the test method name!!
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        Reporter.log("\n=== TestSuccess: "+result.getName()+" ===", true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        String fileName = (result.getName()).replace(":",""); // remove colon
        Reporter.log("\n=== Test Failure: "+fileName+" ===", true);
        fileName = fileName + ".jpg";  // append .jpg
//        Reporter.log("\n"a{background-color\: Color.red; color: Color.white;}""); // TODO make fancier reporting if not built in
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);


        File targetFile = new File("target/surefire-reports/" + fileName);
        Reporter.log("     ----------- screenshot: ", false);
        Reporter.log("\n<a href=\""+fileName+"\"> Click here </a>", false);
        //           <span style="background-color:#00FF00">Green Text</span>

        try {
            FileUtils.copyFile(scrFile, targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        Reporter.log("\n=== Test Skipped: "+result.getName()+" ===", true);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        Reporter.log("\n=== onTestFailedButWithinSuccessPercentage: "+result.getName()+" ===", true);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        Reporter.log("\n=== onTestFailedWithTimeout: "+result.getName()+" ===", true);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        Reporter.log("\n=== Starting ===");
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        Reporter.log("\n=== Finished ===", true);
    }
}
