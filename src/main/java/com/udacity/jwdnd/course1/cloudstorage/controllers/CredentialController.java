package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private CredentialService credentialService;
    private EncryptionService encryptionService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    @PostMapping
    public String addCredential(Authentication authentication, @ModelAttribute("credentialForm") CredentialForm credentialForm, Model model) {
        try{
            User user=userService.getUser(authentication.getName());
            String key=encryptionService.generateKey();
            credentialForm.setPassword(encryptionService.encryptValue(credentialForm.getPassword(),key));
            credentialService.addCredential(new Credential(credentialForm,key,user.getUserid()));

            model.addAttribute("message","Credential added successfully.");
        }catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage", "Credential addition failed.");
        }
        return "result";
    }

    @PutMapping
    public String updateCredential(@ModelAttribute("credentialForm") CredentialForm credentialForm, Model model){
        try{
            String key=encryptionService.generateKey();
            credentialForm.setPassword(encryptionService.encryptValue(credentialForm.getPassword(),key));
            credentialService.updateCredential(new Credential(credentialForm,key,null));
            model.addAttribute("message","Credential updated successfully.");
        } catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","Credential update failed.");
        }
        return "result";

    }
    @DeleteMapping("/{credentialId}")
    public String deleteCredential(@PathVariable int credentialId, Model model){
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("message", "Credential deleted successfully.");
        } catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","Credential deletion failed.");
        }
        return "result";
    }
}



