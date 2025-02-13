package com.socialsync.socialsync.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialsync.socialsync.dto.ProductDto;
import com.socialsync.socialsync.entity.Product;
import com.socialsync.socialsync.exceptions.ProductNotFoundException;
import com.socialsync.socialsync.repository.ProductRepository;
import com.socialsync.socialsync.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setProdId(UUID.randomUUID().toString());
        product.setProdName(productDto.getProdName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(productDto.getCategory());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto, String prodId) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with " + id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
