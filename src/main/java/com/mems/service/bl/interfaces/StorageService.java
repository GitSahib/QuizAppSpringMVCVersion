package com.mems.service.bl.interfaces;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.mems.domain.User;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    //String store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

   // void deleteAll();

	void delete(String fileName);

	String store(MultipartFile file, User user);

}