package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * CredentialPage class
 * prerequisite: user must be logged in and in the home page
 */
public class CredentialPage {

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTabField;

    @FindBy(id = "add-credential")
    private WebElement addCredentialModalButton;

    @FindBy(id = "edit-credential")
    private WebElement editCredentialButton;

    @FindBy(id = "delete-credential")
    private WebElement deleteCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrlField;

    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "credentialSave")
    private WebElement credentialSubmitButton;



    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCredentialsTab() {
        credentialsTabField.click();
    }

    public void clickAddCredentialModalButton() {
        addCredentialModalButton.click();
    }

    public void clickEditCredentialButton() {
        editCredentialButton.click();
    }

    public void clickDeleteCredentialButton() {
        deleteCredentialButton.click();
    }

    public void setCredentialUrlField(String credentialUrl) {
        credentialUrlField.sendKeys(credentialUrl);
    }

    public void setCredentialUsernameField(String credentialUsername) {
        credentialUsernameField.sendKeys(credentialUsername);
    }

    public void setCredentialPasswordField(String credentialPassword) {
        credentialPasswordField.sendKeys(credentialPassword);
    }

    public void clickCredentialSubmitButton() {
        credentialSubmitButton.click();
    }

    public void fillCredentialForm(String credentialUrl, String credentialUsername, String credentialPassword) {
        setCredentialUrlField(credentialUrl);
        setCredentialUsernameField(credentialUsername);
        setCredentialPasswordField(credentialPassword);
    }

    public void resetCredentialForm() {
        credentialUrlField.clear();
        credentialUsernameField.clear();
        credentialPasswordField.clear();
    }

    public void createNewCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        clickCredentialsTab();
        clickAddCredentialModalButton();
        fillCredentialForm(credentialUrl, credentialUsername, credentialPassword);
        clickCredentialSubmitButton();
    }

    public void editCredential(String credentialUrl, String credentialUsername, String credentialPassword) {
        clickCredentialsTab();
        clickEditCredentialButton();
        resetCredentialForm();
        fillCredentialForm(credentialUrl, credentialUsername, credentialPassword);
        clickCredentialSubmitButton();
    }

    public void deleteCredential() {
        clickCredentialsTab();
        clickDeleteCredentialButton();
    }

}
