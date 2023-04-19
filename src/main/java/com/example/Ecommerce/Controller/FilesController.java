package com.example.Ecommerce.Controller;

import com.example.Ecommerce.entity.FileInfo;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ResourceNotFoundException;
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
@RequestMapping("/api/user/v1")
public class FilesController {



    @Autowired
    FilesStorageService storageService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public FilesController(FilesStorageService storageService, UserRepository userRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @PostMapping("/upload/{userId}")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam(name = "file")MultipartFile file, @PathVariable(name = "userId") Integer userId){

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        String message = "";
        //neu chua co image

        if(user.getPhotos() == null){
            storageService.uploadFile(file);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            user.setPhotos(fileName);
            userRepository.save(user);

            System.out.println(fileName);
            message = "Upload the file successfully: " + file.getOriginalFilename();
        }

        if(user.getPhotos() != null && !user.getPhotos().isEmpty()){
            String oldFileName = user.getPhotos();
            storageService.deleteImage(oldFileName);

            //luu anh moi
            storageService.uploadFile(file);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            user.setPhotos(fileName);
            userRepository.save(user);

            message = "Update the file successfully: " + file.getOriginalFilename();
        }
        ResponseMessage responseMessage = new ResponseMessage(message, HttpStatus.OK.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);


    }
    @GetMapping("/listFiles")
    public ResponseEntity<List<FileInfo>> getListFiles(){
        List<FileInfo> listFiles = storageService.getListFiles();

        return ResponseEntity.status(HttpStatus.OK).body(listFiles);

    }

    @GetMapping("/files/{filename:.+}") // dung de download file
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename){
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }





}
