package com.global.testBase;

import org.apache.commons.io.FileUtils;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.util.UUID;

import static com.global.testBase.WebDriverManager.getChromeDriver;

public class ScreenshotListener extends RunListener {

    private TakesScreenshot screenshotTaker;

    @Override
    public void testFailure(Failure failure) throws Exception {
        File scrFile = ((TakesScreenshot) getChromeDriver()).getScreenshotAs(OutputType.FILE);
        String fileName = UUID.randomUUID().toString() + getClass().getName();
        File targetFile = new File("src/test/screenshots" + fileName + ".png");
        FileUtils.copyFile(scrFile, targetFile);
    }

}
