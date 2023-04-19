package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.Controller.FilesController;
import com.example.Ecommerce.entity.FileInfo;
import com.example.Ecommerce.service.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



@Service
public class FilesStorageServiceImpl implements FilesStorageService {

    private final Path root = Paths.get("uploads");

    public void uploadFile(MultipartFile file){
        String message = "";

        try{
            save(file);
           // message = "Upload the file successfully: " + file.getOriginalFilename();
        }catch (Exception e){

            throw new RuntimeException("Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage());
        }

    }

    @Override
    public List<FileInfo> getListFiles() {
        List<FileInfo> fileInfos = loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return fileInfos;
    }

    @Override
    public Resource getFile(String fileName) {

        return load(fileName);
    }





    //    ----------------------------------------------------------------------------------------
    @Override
    public void init() {
        try {
            Files.createDirectories(root);
        }catch (IOException exception){
            throw new RuntimeException("Could not initialize folder for upload");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (!fileName.endsWith(".png") && !fileName.endsWith(".jpg")) {
                throw new RuntimeException("Only PNG and JPG files are allowed.");
            }
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        }catch (Exception exception){
            if (exception instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(exception.getMessage());

        }

    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = (Resource) new UrlResource(file.toUri());

            if (((UrlResource) resource).exists() || ((UrlResource) resource).isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());

    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }

    @Override
    public void deleteImage(String imageName) {
        Path filePath = root.resolve(imageName);
        try{
            Files.delete(filePath);
        }catch(IOException e){

        }
    }
}
