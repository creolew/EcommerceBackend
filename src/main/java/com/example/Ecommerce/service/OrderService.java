package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.order.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> listAll();
}
