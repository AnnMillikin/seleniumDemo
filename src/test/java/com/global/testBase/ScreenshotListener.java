package com.global.testBase;

import com.global.AppTest;
import org.apache.commons.io.FileUtils;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.util.UUID;

import static com.global.testBase.WebDriverManager.getChromeDriver;

public class ScreenshotListener extends RunListener {

//    private TakesScreenshot screenshotTaker;  // TODO how is this used?
//
//    public ScreenshotListener(TakesScreenshot screenshotTaker) {
//        this.screenshotTaker = screenshotTaker;
//    }

    public ScreenshotListener() {
        System.out.println("=== ScreenshotListener constructor ===");

    }

//    public void main(String... args) { // not working
//        JUnitCore core= new JUnitCore();
//        core.addListener(new ScreenshotListener());
//        core.run(AppTest.class);
//    }

    /*
Called when an atomic test is about to be started.
Parameters:
description - the description of the test that is about to be run (generally a class and method name)
 */
    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("=== testStarted === ");
        System.out.println("Starting: " + description.getMethodName().toString());
    }
/*
Called when an atomic test has finished, whether the test succeeds or fails.
Parameters:
description - the description of the test that just ran
 */
    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("=== testFinished === ");
        System.out.println("Finished: " + description.getMethodName().toString());
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        System.out.println("=== testAssumptionFailure === ");
        System.out.println("Failed: " + failure.getDescription().toString());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println("=== testIgnored === ");
        System.out.println("Ignored: " + description.getMethodName().toString());
    }
/*
Called when an atomic test fails, or when a listener throws an exception.
In the case of a failure of an atomic test, this method will be called with the same Description passed to testStarted(Description), from the same thread that called testStarted(Description).

In the case of a listener throwing an exception, this will be called with a Description of Description.TEST_MECHANISM, and may be called on an arbitrary thread.

Parameters:
failure - describes the test that failed and the exception that was thrown
 */
    @Override // TODO  why isn't this being called with a failure
    public void testFailure(Failure failure) throws Exception {
        System.out.println("=== testFailure ====   failure: " + failure.getMessage());
        System.out.println("Failed: " + failure.getDescription().getMethodName().toString());
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);  //  TODO initializationError?
        String fileName = UUID.randomUUID().toString() + failure.getDescription().getMethodName().toString();
        File targetFile = new File("src/test/screenshots" + fileName + ".png");
        FileUtils.copyFile(scrFile, targetFile);
    }

    /*
    Called before any tests have been run. This may be called on an arbitrary thread.
Parameters:
description - describes the tests to be run
     */
    @Override
    public void testRunStarted(Description description) {
        System.out.println(" === testRunStarted ===    method: " + description.getMethodName().toString());
        System.out.println("Number of tests to execute: " + description.testCount());
    }

    /*
    Called when all tests have finished. This may be called on an arbitrary thread.
Parameters:
result - the summary of the test run, including all the tests that failed
     */
    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("=== testRunFinished (all tests have finished) ===    failure count: " + result.getFailureCount());
        System.out.println("result toString: " + result.toString());
    }

}
