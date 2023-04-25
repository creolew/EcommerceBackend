package com.example.Ecommerce.service;

import com.example.Ecommerce.payload.checkout.CheckoutDto;
import com.example.Ecommerce.payload.payment.PaymentDto;

public interface CheckoutService {
    CheckoutDto getAll(PaymentDto paymentDto);
}
