package com.example.Ecommerce.Controller.category;


import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.FileInfo;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.repository.CategoryRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.response.ResponseMessage;
import com.example.Ecommerce.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin("http://localhost:8081")
@RequestMapping("/api/category/v1")
public class UploadImageCategoryController {
    @Autowired
    FilesStorageService storageService;

    @Autowired
    CategoryRepository categoryRepository;

    public UploadImageCategoryController(FilesStorageService storageService, CategoryRepository categoryRepository) {
        this.storageService = storageService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/upload/{categoryId}")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(name = "file") MultipartFile file, @PathVariable(name = "categoryId") Integer categoryId){

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        String message = "";
        //neu chua co image
        String folderName = "uploadsCategoryImages";
        if(category.getImage() == null){
            storageService.uploadFile(file,folderName);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            category.setImage(fileName);
            categoryRepository.save(category);

            System.out.println(fileName);
            message = "Upload the file successfully: " + file.getOriginalFilename();
        }


        //neu co hinh anh
        if(category.getImage() != null && !category.getImage().isEmpty()){
            String oldFileName = category.getImage();
            storageService.deleteImage(oldFileName, folderName);

            //luu anh moi
            storageService.uploadFile(file, folderName);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            category.setImage(fileName);
            categoryRepository.save(category);

            message = "Update the file successfully: " + file.getOriginalFilename();
        }
        ResponseMessage responseMessage = new ResponseMessage(message, HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }


    @GetMapping("/listFiles")
    public ResponseEntity<List<FileInfo>> getListFiles(){
        String folderName = "uploadsCategoryImages";
        List<FileInfo> listFiles = storageService.getListFiles(folderName);

        return ResponseEntity.status(HttpStatus.OK).body(listFiles);

    }

    @GetMapping("/files/{filename:.+}") // dung de download file
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        String folderName = "uploadsCategoryImages";
        Resource file = storageService.load(filename, folderName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }




}
