package com.example.Ecommerce.exception;

import com.example.Ecommerce.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {

        ResponseMessage responseMessage = new ResponseMessage("File is too large", HttpStatus.EXPECTATION_FAILED.value());

        return new ResponseEntity<>(responseMessage, HttpStatus.EXPECTATION_FAILED);
    }
}
