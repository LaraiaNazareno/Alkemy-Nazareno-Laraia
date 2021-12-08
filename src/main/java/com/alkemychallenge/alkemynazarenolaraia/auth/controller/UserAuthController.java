package com.alkemychallenge.alkemynazarenolaraia.auth.controller;



import com.alkemychallenge.alkemynazarenolaraia.auth.dto.AuthenticationRequest;
import com.alkemychallenge.alkemynazarenolaraia.auth.dto.AuthenticationResponse;
import com.alkemychallenge.alkemynazarenolaraia.auth.dto.UserDTO;
import com.alkemychallenge.alkemynazarenolaraia.auth.service.JwtUtils;
import com.alkemychallenge.alkemynazarenolaraia.auth.service.UserDetailsCustomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserDetailsCustomServiceImpl userDetailsService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserDTO user) throws Exception {
        this.userDetailsService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest) throws Exception {

        /*
       AuthenticationResponse authRes = new AuthenticationResponse(this.userDetailsService.createToken(authRequest));

           //    this.userDetailsService.createToken(authRequest);

        return ResponseEntity.ok().body(new AuthenticationResponse(authRes.getJwt()));*/

        return ResponseEntity.ok(new AuthenticationResponse(this.userDetailsService.createToken(authRequest)));


    }
}
