package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.udacity.jwdnd.course1.cloudstorage.pages.NotePage;
import com.udacity.jwdnd.course1.cloudstorage.utils.TestInitializer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NoteTests extends TestInitializer {

    @Test
    public void testAddNote(){
        signUpAndLogin("Note","Test","NT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        NotePage notePage = new NotePage(driver);
        notePage.createNewNote(
                "Note Title",
                "Note Description"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }

    @Test
    public void testEditNote(){
        signUpAndLogin("Edit Note","Test","ENT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        NotePage notePage = new NotePage(driver);
        notePage.createNewNote(
                "Note Title",
                "Note Description"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));

        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
        getPage("/home");
        notePage.editNote( "Edited Note Title", "Edited Note Description");
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
    }

    @Test
    public void testDeleteNote(){
        signUpAndLogin("Delete Note","Test","DNT","123");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(2));
        NotePage notePage = new NotePage(driver);
        notePage.createNewNote(
                "Note Title",
                "Note Description"
        );
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        Assertions.assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));
        getPage("/home");
        notePage.deleteNote();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        assertFalse(driver.findElements(By.id("success")).isEmpty());
        assertTrue(driver.findElements(By.id("error")).isEmpty());
        assertFalse(driver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }
}
