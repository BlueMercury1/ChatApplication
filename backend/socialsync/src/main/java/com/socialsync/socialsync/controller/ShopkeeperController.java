package com.socialsync.socialsync.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialsync.socialsync.dto.ShopKeeperDto;
import com.socialsync.socialsync.entity.ShopKeeper;
import com.socialsync.socialsync.exceptions.ShopKeeperNotFoundException;
import com.socialsync.socialsync.service.ShopkeeperService;

@RestController
public class ShopkeeperController {

    @Autowired
    private ShopkeeperService shopkeeperService;

    @PostMapping("/register")
    public ResponseEntity<?> registerShopkeeper(@RequestBody ShopKeeperDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shopkeeperService.addShopKeeper(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ShopKeeperDto dto) {
        ShopKeeper shopKeeperByEmail = shopkeeperService.getShopKeeperByEmail(dto.getShopKeeperEmail());
        System.err.println(shopKeeperByEmail);
        if (Objects.isNull(shopKeeperByEmail)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ShopKeeper not found");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Login sucessfully");
        }

    }

    @ExceptionHandler(ShopKeeperNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(ShopKeeperNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

}
