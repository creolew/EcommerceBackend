package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.entity.Adress;
import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Payment;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.payload.cartItem.CartItemDto;
import com.example.Ecommerce.payload.checkout.CheckoutDto;
import com.example.Ecommerce.payload.payment.PaymentDto;
import com.example.Ecommerce.repository.AdressRepository;
import com.example.Ecommerce.repository.CartItemRepository;
import com.example.Ecommerce.repository.PaymentRepository;
import com.example.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.service.CartItemService;
import com.example.Ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    AdressRepository adressRepository;

    @Autowired
    PaymentRepository paymentRepository;


    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartItemService cartItemService;



    @Override
    public CheckoutDto getAll(PaymentDto paymentDto) {

        CheckoutDto checkoutDto = new CheckoutDto();

        User customerLoggingIn = getUserLoggingIn();
        Adress adress = adressRepository.findByCustomer(customerLoggingIn);
        List<CartItem> cartItemList= cartItemRepository.findByCustomer(customerLoggingIn);
        Payment p =paymentRepository.findByName(paymentDto.getName()).get();

    //Map to checkoutDto
        checkoutDto.setCity(adress.getCity());
        checkoutDto.setStreet(adress.getStreet());
        checkoutDto.setNumberAdress(adress.getNumberAdress());


//---------------------------------------------------------------
        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        for(CartItem k : cartItemList){
            cartItemDtoList.add(mapToCartItemDto(k));
        }


        checkoutDto.setPaymentName(p.getName());

//---------------------------------------------------------------

        checkoutDto.setTotalPrice(cartItemService.getTotalPrice());


        return checkoutDto;
    }


    private User getUserLoggingIn() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail).get();
        return user;

    }


    private CartItemDto mapToCartItemDto(CartItem cartItem){

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProduct(cartItem.getProduct().getName());
        cartItemDto.setQuantity(cartItem.getQuantity());

        return cartItemDto;
    }
















    }
