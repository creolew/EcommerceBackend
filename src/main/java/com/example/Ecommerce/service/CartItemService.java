package com.example.Ecommerce.service;

import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.cartItem.CartItemDto;
import com.example.Ecommerce.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CartItemService {

    CartItemDto createCart(CartItemDto cartItemDto);

    void updateQuantity(Integer quantity, Integer cartId);

    void deleteCart(Integer cartId);

    List<CartItemDto> listCartByCustomer();

    float getTotalPrice();
}
