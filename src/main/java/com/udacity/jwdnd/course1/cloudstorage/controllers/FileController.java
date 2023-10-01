package com.udacity.jwdnd.course1.cloudstorage.controllers;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String uploadFile(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, @RequestParam MultipartFile fileUpload,Model model){
        try{
            if(fileUpload.isEmpty()){
                throw new Exception(" File is empty");
            }

            User user=userService.getUser(authentication.getName());

            if(!fileService.isFileNameAvailable(fileUpload.getOriginalFilename(),user.getUserid())){
                throw new Exception("File name already exists");
            }

            fileService.addFile(fileUpload, user.getUserid());
            model.addAttribute("message","File uploaded successfully.");

        }catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMessage","File upload failed." + e.getMessage());
        }

        return "result";
    }

    @GetMapping(value = "/{fileId}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> downloadFile(@PathVariable int fileId){
        try{
            File file = fileService.getFileById(fileId);

            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.parseMediaType(file.getContentType()));
            headers.setContentLength(Long.parseLong(file.getFileSize()));
            return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model){
            try {
                fileService.deleteFile(fileId);
                model.addAttribute("message", "File deleted successfully.");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "File deletion failed.");
            }
        return "result";
    }
}
