package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {
    CredentialMapper credentialMapper;
    EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }


    public List<Credential> getAllCredentials(int userId){
        return credentialMapper.findByUserId(userId).stream().peek(
                credential -> credential.setDecryptedPassword(encryptionService.decryptValue(credential.getPassword(),credential.getKey()))).collect(Collectors.toList());
    }

    public int addCredential(Credential credential) {
        return credentialMapper.insert(credential);
    }

    public void updateCredential(Credential credential) throws Exception{
        if(credentialMapper.update(credential) <=0){
            throw new Exception("Error updating credential");
        }
    }

    public void deleteCredential(Integer credentialId) throws Exception {
        if(credentialMapper.delete(credentialId) <=0){
            throw new Exception("Error deleting credential");

        }
    }


}
