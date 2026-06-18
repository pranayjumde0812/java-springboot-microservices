package com.ecommerce.order.clients;

import com.ecommerce.order.dto.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface UserServiceClient {

    @GetExchange("/api/users/{productId}")
    ProductResponse getProductDetails(@PathVariable String productId);
}
