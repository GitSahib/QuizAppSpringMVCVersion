package com.mems.service.security.interfaces;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.mems.domain.Profile;
import com.mems.domain.User;

public interface ProfileService {
    void save(Profile Profile);
    void updateUserProfile(User user, Profile profileForm);
    Profile findByFirstName(String FirstName);
    ResponseEntity<Object> saveImage(MultipartFile uploadfile, User user, Environment env);
	ResponseEntity<String> saveImage(MultipartFile uploadfile, User user);
}
