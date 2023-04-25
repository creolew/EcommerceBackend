package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.adress.AdressDto;

public interface AdressService {

    AdressDto createAdress(AdressDto adressDto);
    AdressDto viewAdressByCustomerId();
}
