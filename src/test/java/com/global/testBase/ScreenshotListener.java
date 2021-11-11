package com.global.testBase;

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

    private TakesScreenshot screenshotTaker;  // TODO how is this used?

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
    public void testFailure(Failure failure) throws Exception {
        System.out.println("=== testFailure ===");
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = UUID.randomUUID().toString() + getClass().getName();
        File targetFile = new File("src/test/screenshots" + fileName + ".png");
        FileUtils.copyFile(scrFile, targetFile);
    }

}
