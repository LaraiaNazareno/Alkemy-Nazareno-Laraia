package com.alkemychallenge.alkemynazarenolaraia.auth.filter;



import com.alkemychallenge.alkemynazarenolaraia.auth.service.JwtUtils;
import com.alkemychallenge.alkemynazarenolaraia.auth.service.UserDetailsCustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsCustomServiceImpl userDetailsCustomServiceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final  String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsCustomServiceImpl.loadUserByUsername(username);

            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authReq =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authReq.setDetails(authReq);
                // Set auth in context
                SecurityContextHolder.getContext().setAuthentication(authReq);
            }
        }
        filterChain.doFilter(request, response);
    }




}
