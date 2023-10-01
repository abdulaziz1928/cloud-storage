package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }


    @PostMapping
    public String addNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model){
        try {
            User user=userService.getUser(authentication.getName());
            noteService.addNote(new Note(noteForm, user.getUserid()));
            model.addAttribute("message","Note added successfully.");
        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","Note addition failed.");
        }
        return "result";
    }

    @PutMapping
    public String updateNote(@ModelAttribute("noteForm") NoteForm noteForm, Model model){
        try {
            noteService.updateNote(noteForm);
            model.addAttribute("message","Note updated successfully.");
        } catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","Note update failed.");
        }
        return "result";
    }
    @DeleteMapping("/{noteId}")
    public String deleteNote(@PathVariable int noteId, Model model){
        try{
            noteService.deleteNote(noteId);
            model.addAttribute("message", "Note deleted successfully.");
        } catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","Note deletion failed.");
        }
        return "result";
    }
}
