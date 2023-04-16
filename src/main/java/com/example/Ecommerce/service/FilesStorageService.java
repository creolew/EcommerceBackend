package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public interface FilesStorageService {

    public void init();

    public void save(MultipartFile file);

    public Resource load(String fileName);

    public void deleteAll();

    public Stream<Path> loadAll();

    void uploadFile(MultipartFile file);

    List<FileInfo> getListFiles();

    Resource getFile(String fileName);

    void deleteImage(String imageName);
}
