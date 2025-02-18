package com.socialsync.socialsync.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.JwtException;

// @RestControllerAdvice
// public class JwtExceptionHandler {

// @ExceptionHandler(JwtException.class)
// public ResponseEntity<?> handleJwtException(JwtException e) {
// Map<String, Object> map = new HashMap<>();
// map.put("message", e.getMessage());
// map.put("status", HttpStatus.UNAUTHORIZED);

// return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
// }

// }
