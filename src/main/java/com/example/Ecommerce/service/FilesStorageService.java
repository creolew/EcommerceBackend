package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.FileInfo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;


public interface FilesStorageService {


    public void save(MultipartFile file, String folderName);

    public Resource load(String fileName, String folderName);

    public Stream<Path> loadAll(String folderName);

    void uploadFile(MultipartFile file,  String folderName);

    List<FileInfo> getListFiles(String folderName);

    void deleteImage(String imageName, String folderName);
}
