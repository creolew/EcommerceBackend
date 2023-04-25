package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.OrderDetail;
import com.example.Ecommerce.payload.order.OrderDetailDto;
import com.example.Ecommerce.payload.order.OrderDto;
import com.example.Ecommerce.repository.OrderDetailRepository;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetailDto> listAll(int orderId) {

        Order order = orderRepository.findById(orderId).get();

        List<OrderDetailDto> orderDetailDtoList = orderDetailRepository.findByOrder(order).stream().map(k -> mapToDto(k)).collect(Collectors.toList());


        return orderDetailDtoList;
    }

    private OrderDetailDto mapToDto(OrderDetail orderDetail){
        OrderDetailDto orderDetailDto = new OrderDetailDto();

        orderDetailDto.setProductName(orderDetail.getProduct().getName());
        orderDetailDto.setProductCost(orderDetail.getProduct().getCost());
        orderDetailDto.setQuantity(orderDetail.getQuantity());

        return orderDetailDto;
    }
}
