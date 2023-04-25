package com.example.Ecommerce.controller.checkout;

import com.example.Ecommerce.payload.checkout.CheckoutDto;
import com.example.Ecommerce.payload.payment.PaymentDto;
import com.example.Ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/checkout/v1")
public class CheckoutController {

    @Autowired
    CheckoutService checkoutService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/getCheckout")
    public CheckoutDto getCheckout(@RequestBody PaymentDto paymentDto){
        return checkoutService.getAll(paymentDto);
    }
}
