package com.example.Ecommerce.controller.adress;

import com.example.Ecommerce.payload.adress.AdressDto;
import com.example.Ecommerce.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/adress/v1")
public class AdressController {

    @Autowired
    AdressService adressService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/createAdress")
    public ResponseEntity<AdressDto> createAdress( @RequestBody AdressDto adressDto){
        AdressDto result = adressService.createAdress(adressDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/viewByCustomer")
    public ResponseEntity<AdressDto> viewByCustomer(){
        AdressDto adressDto = adressService.viewAdressByCustomerId();
        return new ResponseEntity<>(adressDto, HttpStatus.CREATED);

    }


}
