package com.socialsync.socialsync.service;

import java.util.List;

import com.socialsync.socialsync.dto.ProductDto;
import com.socialsync.socialsync.entity.Product;

public interface ProductService {
    Product addProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto, String prodId);

    void deleteProduct(String id);

    Product getProduct(String id);

    List<Product> getAllProducts();
}
