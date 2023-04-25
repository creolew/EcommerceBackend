package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Adress;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.adress.AdressDto;
import com.example.Ecommerce.repository.AdressRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AdressServiceImpl implements AdressService {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public AdressDto createAdress(AdressDto adressDto) {

        User customer = getUserLoggingIn();

        Adress adress = new Adress();

        adress.setCity(adressDto.getCity());
        adress.setNumberAdress(adressDto.getNumberAdress());
        adress.setStreet(adressDto.getStreet());
        adress.setCustomer(customer);

        adressRepository.save(adress);



        return mapToDto(adress);
    }

    @Override
    public AdressDto viewAdressByCustomerId() {

        User customer = getUserLoggingIn();
        Adress adress = adressRepository.findByCustomer(customer);

        return mapToDto(adress);
    }


    private AdressDto mapToDto(Adress adress){
        AdressDto adressDto = new AdressDto();

        adressDto.setNumberAdress(adress.getNumberAdress());
        adressDto.setCity(adress.getCity());
        adressDto.setStreet(adress.getStreet());
//        adressDto.setCustomerName(adress.getCustomer().getUsername());

        return adressDto;
    }

    private User getUserLoggingIn() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail).get();
        return user;
    }
}
