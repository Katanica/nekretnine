package com.example.backend.Security;
import org.springframework.context.annotation.Lazy;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


// IZVRSAVA SE PRI SVAKOM REQUESTU I PROVJERAVA IMA LI REQUEST TOKEN
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        // 1. UZMI HEADER
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // IZ HEADERA UZMI SVE POSLIJE 7 indexa tj. poslije "Bearer"
            String token = authHeader.substring(7);

            // provjeri je li token validan
            if (jwtUtil.isTokenValid(token)) {

                // ako je validan iz tokena izvuci email - svaki token sadrzi i email
                String email = jwtUtil.extractEmail(token);
                System.out.println("Email iz tokena: " + email);
                // nadi tog korisnika
                var userDetails = userDetailsService.loadUserByUsername(email);

                // upisi tko je ulogovani korisnik te mozes bilo gdje u aplikaciji provjeriti tko je ulogovan
                var auth = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        // pusti ga dalje - jwtfilter pusta sviju, a SecurityConfig kontrolira
        chain.doFilter(request, response);
    }
}

