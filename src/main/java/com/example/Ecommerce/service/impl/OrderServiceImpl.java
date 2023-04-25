package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.payload.order.OrderDto;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<OrderDto> listAll() {
        List<OrderDto> listOrdersDto = orderRepository.findAll().stream().map(order -> mapToDto(order)).collect(Collectors.toList());;

        return listOrdersDto;
    }



    private OrderDto mapToDto(Order order){
        OrderDto orderDto = new OrderDto();

        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setUsername(order.getUsername());
        orderDto.setProductCost(order.getProductCost());
        orderDto.setLastName(order.getLastName());
        orderDto.setFirstName(order.getFirstName());

        return  orderDto;

    }
}
