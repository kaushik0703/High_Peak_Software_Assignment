package com.highpeak.qa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {

    /**
     * Reusable utility method to capture screenshots.
     * 
     * @param driver         WebDriver instance
     * @param screenshotName Name of the screenshot
     * @return Path where screenshot is saved
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = screenshotName + "_" + timestamp + ".png";
        String directory = System.getProperty("user.dir") + "/screenshots/";

        File folder = new File(directory);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationPath = new File(directory + fileName);

        try {
            FileUtils.copyFile(sourcePath, destinationPath);
        } catch (IOException e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }

        return destinationPath.getAbsolutePath();
    }
}
