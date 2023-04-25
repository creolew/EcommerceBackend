package com.example.Ecommerce.payload.checkout;

import com.example.Ecommerce.entity.Adress;
import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Payment;
import com.example.Ecommerce.payload.adress.AdressDto;
import com.example.Ecommerce.payload.cartItem.CartItemDto;
import com.example.Ecommerce.payload.payment.PaymentDto;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutDto {

//    Adress adress;
    private String city;

    private String street;

    private String numberAdress;





//    Payment payment;

    String paymentName;



//----------------------
    private float totalPrice;


}
