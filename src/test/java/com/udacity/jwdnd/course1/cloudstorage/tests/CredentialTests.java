package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialPage;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CredentialTests extends TestInitializer {

    @Test
    public void testAddCredential(){
        signUpAndLogin("Credential","Test","CT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.createNewCredential(
                "www.test.com",
                "username",
                "password"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }

    @Test
    public void testEditCredential(){
        signUpAndLogin("Edit Credential","Test","ECT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.createNewCredential(
                "www.test.com",
                "username",
                "password"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
        getPage("/home");
        credentialPage.editCredential(
                "www.edited-test.com",
                "edited-username",
                "edited-password"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }

    @Test
    public void testDeleteCredential(){
        signUpAndLogin("Delete Credential","Test","DCT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.createNewCredential(
                "www.test.com",
                "username",
                "password"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
        getPage("/home");
        credentialPage.deleteCredential();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }
}
