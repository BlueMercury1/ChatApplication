package com.socialsync.socialsync.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ProductDto {
    private String prodId;
    private String prodName;
    private String description;
    private double price;
    private String category;
    private Date createdAt;
    private Date updatedAt;
}
