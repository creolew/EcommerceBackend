package com.example.Ecommerce.repository;

import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    public List<CartItem> findByCustomer(User customer);

    public CartItem findByCustomerAndProduct(User customer, Product product);



}
