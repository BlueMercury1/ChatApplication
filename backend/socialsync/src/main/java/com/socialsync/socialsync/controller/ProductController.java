package com.socialsync.socialsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socialsync.socialsync.dto.ProductDto;
import com.socialsync.socialsync.exceptions.ProductNotFoundException;
import com.socialsync.socialsync.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductByid(@PathVariable("id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductByid(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted with id " + id);
    }

    @PostMapping("/")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.addProduct(dto));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
