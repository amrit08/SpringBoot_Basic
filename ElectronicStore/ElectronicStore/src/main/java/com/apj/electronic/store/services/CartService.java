package com.apj.electronic.store.services;

import com.apj.electronic.store.dtos.AddItemToCartRequest;
import com.apj.electronic.store.dtos.CartDto;

public interface CartService {
    //add items to cart
    //case 1: if cart for user is not available:then create the cart and then
    //        add the items to cart .

    //case 2: else if (cart is available ) then add the items to cart .

    CartDto addItemToCart(String userId, AddItemToCartRequest request);

    //remove item from cart :

    void  removeItemFromCart(String userId,int cartItem);

    //remove all items from cart :

    void clearCart(String userId);

    //get cart by user :

    CartDto getCartByUser(String userId);


}
