package com.socialsync.socialsync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socialsync.socialsync.dto.ShopKeeperDto;
import com.socialsync.socialsync.exceptions.ShopKeeperNotFoundException;
import com.socialsync.socialsync.service.ShopkeeperService;
import com.socialsync.socialsync.util.JwtUtil;

@RestController
public class ShopkeeperController {

    @Autowired
    private ShopkeeperService shopkeeperService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerShopkeeper(@RequestBody ShopKeeperDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shopkeeperService.addShopKeeper(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ShopKeeperDto dto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                dto.getShopKeeperEmail(), dto.getShopKeeperPassword());

        Authentication authenticatedUser = authenticationManager.authenticate(authenticationToken);

        if (authenticatedUser.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(jwtUtil.generateToken(dto.getShopKeeperEmail()));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failure");
    }

    @ExceptionHandler(ShopKeeperNotFoundException.class)
    public ResponseEntity<?> exceptionHandler(ShopKeeperNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    // BadCredentialsException
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> exceptionHandlerForBadCredential(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> exceptionHandlerForInternalAuthenticationServiceException(
            InternalAuthenticationServiceException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

}
