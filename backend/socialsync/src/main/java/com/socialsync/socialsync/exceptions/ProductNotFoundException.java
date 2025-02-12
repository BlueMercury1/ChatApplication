package com.socialsync.socialsync.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
