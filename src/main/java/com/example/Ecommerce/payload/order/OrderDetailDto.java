package com.example.Ecommerce.payload.order;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class OrderDetailDto {

    private int quantity;
    private float productCost;

    private String productName;

//    private int orderId;
}
