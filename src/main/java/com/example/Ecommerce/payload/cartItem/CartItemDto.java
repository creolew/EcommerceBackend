package com.example.Ecommerce.payload.cartItem;

import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CartItemDto {


    private int quantity;

    private String product;


}
