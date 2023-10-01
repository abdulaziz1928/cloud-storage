package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getAllNotes(int userId){
        return noteMapper.findByUserId(userId);
    }

    public int addNote(Note note){
       return noteMapper.insert(note);
    }
    public void deleteNote(int noteId) throws Exception {
        if(noteMapper.delete(noteId) <=0){
            throw new Exception("Error deleting note");
        }
    }
    public void updateNote(NoteForm note) throws Exception{
        if(noteMapper.update(note) <=0){
            throw new Exception("Error updating note");
        }
    }

}
