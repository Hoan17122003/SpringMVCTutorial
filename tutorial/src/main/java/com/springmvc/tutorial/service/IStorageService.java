package com.springmvc.tutorial.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {
    public String storeFile(MultipartFile file);

    public Stream<Path> loadAll();// load all file inside a folder

    public byte[] readFileContent(String fileName);

    public void deletedAllFile();

    public boolean deleteFileByUrl(String fileUrl);

}
