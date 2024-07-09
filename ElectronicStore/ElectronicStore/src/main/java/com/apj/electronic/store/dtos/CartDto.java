package com.apj.electronic.store.dtos;

import com.apj.electronic.store.entities.CartItem;
import com.apj.electronic.store.entities.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDto {

    private  String cartId;
    private Date createdAt;
    private UserDto user;
    private List<CartItemDto> items =  new ArrayList<>();

}
