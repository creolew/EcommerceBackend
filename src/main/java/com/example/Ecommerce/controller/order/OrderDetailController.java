package com.example.Ecommerce.controller.order;


import com.example.Ecommerce.payload.order.OrderDetailDto;
import com.example.Ecommerce.payload.order.OrderDto;
import com.example.Ecommerce.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orderDetail/v1")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SELLER')")
    @GetMapping("/getAll/{orderId}")
    public List<OrderDetailDto> getAllOrderDetails(@PathVariable(name = "orderId") int orderId){

        return orderDetailService.listAll(orderId);
    }
}
