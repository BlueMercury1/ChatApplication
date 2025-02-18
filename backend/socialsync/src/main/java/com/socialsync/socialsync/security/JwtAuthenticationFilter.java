package com.socialsync.socialsync.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.socialsync.socialsync.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static int hitctr = 0;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomShopkeeperDetaillsService customShopkeeperDetaillsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.err.println("\n\t\t**** Hit for JwtAuthenticationFilter " + ++hitctr + " ****\n");

        String header = request.getHeader("Authorization");

        System.out.println(header);

        if (header != null && header.startsWith("Bearer")) {
            String jwtToken = header.substring(7);
            String username = jwtUtil.getUsernameFromToken(jwtToken);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.err.println(username + "  " + authentication);
            if (authentication == null && username != null) {
                UserDetails userDetails = customShopkeeperDetaillsService.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwtToken, userDetails)) {
                    UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    newAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(newAuthentication);

                }
            }

            System.err.println(jwtToken);
            filterChain.doFilter(request, response);
        }
        filterChain.doFilter(request, response);
    }

}
