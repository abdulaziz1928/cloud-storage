package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * FilePage class
 * prerequisite: user must be logged in and in the home page
 */
public class FilePage {

    @FindBy(id="nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "fileUpload")
    private WebElement fileUploadField;

    @FindBy(id="uploadButton")
    private WebElement uploadButton;

    public FilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public void openFileTab() {
        filesTab.click();
    }

    public void uploadFile(String filePath) {
        fileUploadField.sendKeys(filePath);
        uploadButton.click();
    }
}
