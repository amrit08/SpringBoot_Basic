package com.apj.electronic.store.dtos;

import com.apj.electronic.store.entities.Order;
import com.apj.electronic.store.entities.Product;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString


public class OrderItemDto
{

    private  int orderItemId;
    private  int quantity;
    private  int totalPrice;
    private ProductDto product;

}
