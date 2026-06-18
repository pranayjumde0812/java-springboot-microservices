package com.ecommerce.order.dto.request;

import lombok.Data;

@Data
public class CartItemRequest {

    private String productId;
    private Integer quantity;

}
