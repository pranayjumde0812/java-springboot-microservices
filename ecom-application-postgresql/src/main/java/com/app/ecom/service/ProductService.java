package com.app.ecom.service;

import com.app.ecom.dtos.request.ProductRequest;
import com.app.ecom.dtos.response.ProductResponse;
import com.app.ecom.model.Product;
import com.app.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductRequestToProduct(productRequest, product);

        Product createdProduct = productRepository.save(product);

        return updateProductToProductResponse(createdProduct);

    }

    public Optional<ProductResponse> updateProduct(ProductRequest productRequest, Long productId) {

        return productRepository.findById(productId)
                .map(existingProduct -> {
                    updateProductRequestToProduct(productRequest, existingProduct);
                    Product updatedProduct = productRepository.save(existingProduct);
                    return updateProductToProductResponse(updatedProduct);
                });

    }


    public List<ProductResponse> getAllProducts() {
        return productRepository.findByIsActiveTrue()
                .stream()
                .map(this::updateProductToProductResponse)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> getProductById(Long productId) {
        return productRepository.findById(productId)
                .map(this::updateProductToProductResponse);
    }


    private void updateProductRequestToProduct(ProductRequest productRequest, Product product) {
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setCategory(productRequest.getCategory());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());

    }

    private ProductResponse updateProductToProductResponse(Product product) {

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId().toString());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setCategory(product.getCategory());
        productResponse.setPrice(product.getPrice());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setIsActive(product.getIsActive());

        return productResponse;

    }

    public boolean deleteProduct(Long productId) {
        return false;
    }

    public List<ProductResponse> searchedProducts(String keyword) {

        return productRepository.searchProducts(keyword)
                .stream()
                .map(this::updateProductToProductResponse)
                .collect(Collectors.toList());
    }
}
