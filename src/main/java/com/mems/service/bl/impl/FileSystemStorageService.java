package com.mems.service.bl.impl;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mems.domain.User;
import com.mems.exceptions.StorageException;
import com.mems.exceptions.StorageFileNotFoundException;
import com.mems.service.bl.interfaces.StorageService;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(Environment env) {
        this.rootLocation = Paths.get(env.getProperty("user.profile.uploadedImages"));
    }

    @Override
    public String store(MultipartFile file,User user) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            long currentMillis = System.currentTimeMillis();
            String fileName = user.getId().toString()+"_" + currentMillis+".png";
            Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
            Files.delete(this.rootLocation.resolve(user.getProfile().getImage()));
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void delete(String fileName) {
    	
    	String oldFilePath = Paths.get(rootLocation.getRoot().toString(), fileName).toString();
		File oldFile = new File(oldFilePath);
		oldFile.delete();
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}