package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * NotePage class
 * prerequisite: user must be logged in and in the home page
 */
public class NotePage {

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTabField;

    @FindBy(id = "add-note")
    private WebElement addNoteModalButton;

    @FindBy(id = "edit-note")
    private WebElement editNoteButton;

    @FindBy(id = "delete-note")
    private WebElement deleteNoteButton;

    @FindBy(id = "note-title")
    private WebElement noteTitleField;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;

    @FindBy(id = "noteSave")
    private WebElement noteSubmitButton;

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickNotesTab() {
        notesTabField.click();
    }

    public void clickAddNoteModalButton() {
        addNoteModalButton.click();
    }

    public void clickEditNoteButton() {
        editNoteButton.click();
    }

    public void setNoteTitleField(String noteTitle) {
        noteTitleField.sendKeys(noteTitle);
    }

    public void setNoteDescriptionField(String noteDescription) {
        noteDescriptionField.sendKeys(noteDescription);
    }

    public void clickNoteSubmitButton() {
        noteSubmitButton.click();
    }

    public void fillNoteForm(String noteTitle, String noteDescription) {
        setNoteTitleField(noteTitle);
        setNoteDescriptionField(noteDescription);
    }
    public void resetNoteForm() {
        noteDescriptionField.clear();
        noteTitleField.clear();
    }

    public void createNewNote(String noteTitle, String noteDescription) {
        clickNotesTab();
        clickAddNoteModalButton();
        fillNoteForm(noteTitle, noteDescription);
        clickNoteSubmitButton();
    }

    public void editNote(String noteTitle, String noteDescription) {
        clickNotesTab();
        clickEditNoteButton();
        resetNoteForm();
        fillNoteForm(noteTitle, noteDescription);
        clickNoteSubmitButton();

    }
    public void deleteNote() {
        clickNotesTab();
        deleteNoteButton.click();
    }



}
