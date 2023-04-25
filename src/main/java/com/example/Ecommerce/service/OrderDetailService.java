package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.order.OrderDetailDto;
import com.example.Ecommerce.payload.order.OrderDto;

import java.util.List;

public interface OrderDetailService {
    List<OrderDetailDto> listAll(int orderId);
}
