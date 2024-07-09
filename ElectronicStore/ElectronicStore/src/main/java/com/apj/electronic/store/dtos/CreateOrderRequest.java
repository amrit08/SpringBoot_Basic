package com.apj.electronic.store.dtos;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreateOrderRequest {

    @NotBlank(message = "Cart Id is required  !!!")
   private  String cartId;

    @NotBlank(message = "User Id is required  !!!")
   private String userId;


    private  String orderStatus = "PENDING";
    private  String paymentStatus = "NOT PAID";

    @NotBlank(message = "Address is required  !!!")
    private String billingAddress;

    @NotBlank(message = "billing phone  is required  !!!")
    private String billingPhone;

    @NotBlank(message = "Billing name  is required  !!!")
    private String billingName;


}
