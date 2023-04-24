package com.example.Ecommerce.controller.customer;

import com.example.Ecommerce.payload.customer.CustomerDto;
import com.example.Ecommerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CustomerDto customerDto) {
        return customerService.saveCustomer(customerDto);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return customerService.confirmEmail(confirmationToken);
    }



}
