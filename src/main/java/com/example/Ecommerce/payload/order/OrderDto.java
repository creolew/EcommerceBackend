package com.example.Ecommerce.payload.order;

import com.example.Ecommerce.entity.OrderDetail;
import com.example.Ecommerce.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
public class OrderDto {


    private String username;

    private String firstName;

    private String lastName;

    private Date orderTime;

    private float productCost;

    private int customerId;

//    private Set<OrderDetail> orderDetails = new HashSet<>();
}
