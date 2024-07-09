package com.apj.electronic.store.controllers;

import com.apj.electronic.store.dtos.AddItemToCartRequest;
import com.apj.electronic.store.dtos.ApiResponseMessage;
import com.apj.electronic.store.dtos.CartDto;
import com.apj.electronic.store.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    //add items to cart
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(
            @PathVariable String userId,
            @RequestBody AddItemToCartRequest addItemToCartRequest)
    {
        CartDto cartDto = cartService.addItemToCart(userId, addItemToCartRequest);
        return  new ResponseEntity<>(cartDto , HttpStatus.CREATED);

    }

    // Remove Item from Cart
    @DeleteMapping("/{userId}/items/{itemId}")
    public  ResponseEntity<ApiResponseMessage> removeItemFromCart(
            @PathVariable Integer itemId,
            @PathVariable String userId
    ){
        cartService.removeItemFromCart(userId,itemId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Item is removed")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return  new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    //Clear cart
    @DeleteMapping("/{userId}")
    public  ResponseEntity<ApiResponseMessage> clearCart(
            @PathVariable String userId
    ){
        cartService.clearCart(userId);
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message("Cart is clear !!!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return  new ResponseEntity<>(responseMessage,HttpStatus.OK);
    }

    //Get cart by user
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getUserCart(
            @PathVariable String userId)
    {
        CartDto cartDto = cartService.getCartByUser(userId);
        return  new ResponseEntity<>(cartDto , HttpStatus.OK);

    }



}
