package com.apj.electronic.store.services.impl;
import com.apj.electronic.store.dtos.AddItemToCartRequest;
import com.apj.electronic.store.dtos.CartDto;
import com.apj.electronic.store.entities.Cart;
import com.apj.electronic.store.entities.CartItem;
import com.apj.electronic.store.entities.Product;
import com.apj.electronic.store.entities.User;
import com.apj.electronic.store.exceptions.BadApiRequest;
import com.apj.electronic.store.exceptions.ResourceNotFoundException;
import com.apj.electronic.store.repositories.CartItemRepository;
import com.apj.electronic.store.repositories.CartRepository;
import com.apj.electronic.store.repositories.ProductRepository;
import com.apj.electronic.store.repositories.UserRepository;
import com.apj.electronic.store.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest request)
    {
        int quantity = request.getQuantity();
        String productId = request.getProductId();

        if (quantity<=0)
        {
            throw new BadApiRequest("request quantity is not valid !!!!");

        }

        // fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new
                ResourceNotFoundException("Product not found in database !!!!"));

        //fetch the user from DB

        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("Userid not available in database !!!!!"));

        //fetch the cart from user using cart repository

        Cart cart = null;
        try
        {
           cart =  cartRepository.findByUser(user).get();
        }catch (NoSuchElementException e){
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }
        //perform cart operations

        //if cart items already presents then update the quantity
        //boolean updated=false;
        AtomicReference<Boolean> updated = new AtomicReference<>(false);
        List<CartItem> items = cart.getItems();
        items = items.stream().map((item) -> {

            if (item.getProduct().getProductId().equals(productId)) {
                //item present in cart
                    item.setQuantity(quantity);
                    item.setTotalPrice(quantity*product.getDiscountedPrice());
                    updated.set(true);

            }

            return item;
        }).collect(Collectors.toList());
       // cart.setItems(updatedItems);

        //create items
       if(!updated.get())
       {
           CartItem cartItem = CartItem.builder()
                   .quantity(quantity)
                   .totalPrice(quantity * product.getDiscountedPrice())
                   .cart(cart)
                   .product(product)
                   .build();
           cart.getItems().add(cartItem);

       }

        cart.setUser(user);
        Cart updatedCart = cartRepository.save(cart);
        return modelMapper.map(updatedCart,CartDto.class);
    }

    @Override
    public void removeItemFromCart(String userId, int cartItem)
    {
        CartItem cartItem1 = cartItemRepository.findById(cartItem).orElseThrow(() -> new
                ResourceNotFoundException("cart item with this id not found in this db "));
                cartItemRepository.delete(cartItem1);

    }

    @Override
    public void clearCart(String userId)
    {
        //fetch the user from DB
        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("Userid not available in database !!!!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new
                ResourceNotFoundException("Cart of given user not found"));
        cart.getItems().clear();
        cartRepository.save(cart);

    }

    @Override
    public CartDto getCartByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new
                ResourceNotFoundException("Userid not available in database !!!!!"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new
                ResourceNotFoundException("Cart of given user not found"));
        return  modelMapper.map(cart,CartDto.class);
    }


}
