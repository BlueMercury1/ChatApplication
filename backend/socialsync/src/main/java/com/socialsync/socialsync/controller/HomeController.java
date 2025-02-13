package com.socialsync.socialsync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "This is your home page";
    }

    @GetMapping("/auth/show")
    public ResponseEntity<String> show() {
        return ResponseEntity.ok("showing the auth page");
    }

    @GetMapping("/api/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is after login test page");
    }

    @GetMapping("/api/test1")
    public ResponseEntity<String> test1() {
        return ResponseEntity.ok("This is after login test page-1");
    }

    @GetMapping("/api/test2")
    public ResponseEntity<String> test2() {
        return ResponseEntity.ok("This is after login test page-2");
    }

    @GetMapping("/api/csrf")
    public CsrfToken generateCsrf(HttpServletRequest httpServletRequest) throws Exception {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

}
