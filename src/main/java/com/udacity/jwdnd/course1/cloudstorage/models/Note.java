package com.udacity.jwdnd.course1.cloudstorage.models;

public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private int userId;

    public Note(Integer noteId, String noteTitle, String noteDescription, int userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }
    public Note(NoteForm form,int userId){
        this.noteTitle=form.getTitle();
        this.noteDescription= form.getDescription();
        this.userId=userId;
    }


    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
