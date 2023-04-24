package com.example.Ecommerce.controller.cartItem;

import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.payload.cartItem.CartItemDto;
import com.example.Ecommerce.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cart/v1")
public class CartItemController {

    @Autowired
    CartItemService cartItemService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/createCart")
    public ResponseEntity<CartItemDto> createCartItem(@RequestBody CartItemDto cartItemDto){

        CartItemDto result = cartItemService.createCart(cartItemDto);

        return new ResponseEntity<>(result, HttpStatus.CREATED);

    }


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PutMapping("/updateQuantity/{cartId}")
    public String updateQuantity(@RequestParam (name = "quantity") int quantity, @PathVariable(name = "cartId") int cartId) {
        cartItemService.updateQuantity(quantity, cartId);

        return "Update quantity successfully";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @DeleteMapping("/deleteCart/{cartId}")
    public String deleteCart(@PathVariable (name = "cartId") int cartId){
        cartItemService.deleteCart(cartId);
        return "Delete cart successfully";
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/getCartsByCustomer")
    public List<CartItemDto> getAllCartsByCustomerLoggingIn(){

        return cartItemService.listCartByCustomer();
    }


    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/getToTalPrice")
    public String getToTalPrice(){
        float totalPrice =cartItemService.getTotalPrice();

        return "Total Price: " + totalPrice;
    }







}
