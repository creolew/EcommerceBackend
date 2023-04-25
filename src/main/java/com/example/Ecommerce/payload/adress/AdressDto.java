package com.example.Ecommerce.payload.adress;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AdressDto {

    private String city;

    private String street;

    private String numberAdress;

//    private String customerName;
}
