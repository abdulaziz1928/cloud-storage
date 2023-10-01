package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private FileMapper fileMapper;
    private UserService userService;

    public FileService(FileMapper fileMapper, UserService userService) {
        this.fileMapper = fileMapper;
        this.userService = userService;
    }
    public List<File> getAllFiles(int userId){
        return fileMapper.findByUserId(userId);
    }
    public File getFileById(int fileId){
        return fileMapper.findById(fileId);
    }

    public void deleteFile(int fileId) throws Exception{
        if(fileMapper.delete(fileId) <=0){
            throw new Exception("Error deleting file");
        }
    }
    public boolean isFileNameAvailable(String fileName, int userId){
        return fileMapper.findByFileName(fileName, userId) == null;
    }
    public boolean addFile(MultipartFile file,Integer userId){
            try {
                fileMapper.insert(new File(file, userId));
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;

            }


    }
}
