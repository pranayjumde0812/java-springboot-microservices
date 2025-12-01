package com.app.ecom.dtos.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private String id;
    private String userName;
    private String userId;
    private String productName;
    private String productId;
    private Integer quantity;
    private BigDecimal price;
}
