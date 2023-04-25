package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    List<OrderDetail> findByOrder(Order order);
}
