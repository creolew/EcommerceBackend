package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Category;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.exception.ResourceNotFoundException;
import com.example.Ecommerce.payload.cartItem.CartItemDto;
import com.example.Ecommerce.payload.category.CategoryDto;
import com.example.Ecommerce.repository.CartItemRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public CartItemDto createCart(CartItemDto cartItemDto) {

        Product product = productRepository.findByName(cartItemDto.getProduct());


        User customer = getUserLoggingIn();


        CartItem cartItem = new CartItem();

        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItem.setCustomer(customer);
        cartItem.setProduct(product);

        CartItem savedCartItem =cartItemRepository.save(cartItem);



        return mapToDto(savedCartItem);
    }

    @Override
    public void updateQuantity(Integer quantity, Integer cartId) {

        CartItem cart = cartItemRepository.findById(cartId).get();

        cart.setQuantity(quantity);

        cartItemRepository.save(cart);

    }

    @Override
    public void deleteCart(Integer cartId) {
        User customer = getUserLoggingIn();
        CartItem cart = cartItemRepository.findById(cartId).get();

        if(cart.getCustomer().getId()  == customer.getId()){
            cartItemRepository.delete(cart);
        }else{
            throw new ResourceNotFoundException("Cart", "id",cartId);
        }

    }

    @Override
    public List<CartItemDto> listCartByCustomer() {
        User customerLoggingIn = getUserLoggingIn();

        List<CartItem> cartItemList= cartItemRepository.findByCustomer(customerLoggingIn);

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(CartItem k : cartItemList){
            cartItemDtoList.add(mapToDto(k));
        }

        return cartItemDtoList;
    }

    @Override
    public float getTotalPrice() {
        float totalPrice = 0;
        User customerLoggingIn = getUserLoggingIn();

        List<CartItem> cartItemList= cartItemRepository.findByCustomer(customerLoggingIn);

        for(CartItem cart : cartItemList){
            System.out.println(cart);
            float cost = cart.getProduct().getCost();
            int quantity = cart.getQuantity();
            totalPrice += quantity * cost;
        }

        return totalPrice;
    }


    private CartItemDto mapToDto(CartItem cartItem){

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProduct(cartItem.getProduct().getName());
        cartItemDto.setQuantity(cartItem.getQuantity());

        return cartItemDto;
    }

    //get user who is logging in
    private User getUserLoggingIn() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail).get();
        return user;
    }


}
