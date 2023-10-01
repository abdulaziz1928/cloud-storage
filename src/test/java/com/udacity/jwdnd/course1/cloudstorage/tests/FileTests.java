package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.FilePage;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestInitializer;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileTests extends TestInitializer {

    @Test
    public void testUploadFile(){
        signUpAndLogin("File1","Test1","FT1","123");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String fileName = "upload5m.zip";

        FilePage filePage = new FilePage(driver);
        filePage.openFileTab();
        filePage.uploadFile(new File(fileName).getAbsolutePath());
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }

    @Test
    public void testUploadFileWithSameName(){
        signUpAndLogin("Large File2","Test2","FT2","123");

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String fileName = "upload5m.zip";

        FilePage filePage = new FilePage(driver);
        filePage.openFileTab();
        filePage.uploadFile(new File(fileName).getAbsolutePath());
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

        getPage("/home");
        filePage.uploadFile(new File(fileName).getAbsolutePath());
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("error")));
        assertFalse(driver.findElements(By.id("error")).isEmpty());
        assertTrue(driver.findElements(By.id("success")).isEmpty());

    }
}
