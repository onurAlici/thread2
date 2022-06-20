package com.forum.thread.authentication.filters;


import com.forum.thread.authentication.components.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class FirstFilter extends OncePerRequestFilter {


    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;



    @Value("${jwt.signing.key}")
    private String signingKey;



    public static final Logger log = LoggerFactory.getLogger(FirstFilter.class);




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");

        String code = request.getHeader("Authorization");


        if (code == null) {
            Authentication a = new UsernamePasswordAuthentication(username, password);
            Authentication auth = authenticationManager.authenticate(a);

            SecurityContextHolder.getContext().setAuthentication(auth);
            SecretKey key = Keys.hmacShaKeyFor(signingKey.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .setClaims(Map.of("username", username))
                    .signWith(key)
                    .compact();
            response.setHeader("Authorization", jwt);
            response.addHeader("Access-Control-Expose-Headers", "Authorization");

            filterChain.doFilter(request, response);





        } else {
            filterChain.doFilter(request, response);
        }


    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/user/login");
    }

}
