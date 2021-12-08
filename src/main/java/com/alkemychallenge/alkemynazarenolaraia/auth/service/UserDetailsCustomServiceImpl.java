package com.alkemychallenge.alkemynazarenolaraia.auth.service;


import com.alkemychallenge.alkemynazarenolaraia.auth.dto.AuthenticationRequest;
import com.alkemychallenge.alkemynazarenolaraia.auth.dto.UserDTO;
import com.alkemychallenge.alkemynazarenolaraia.auth.entity.UserEntity;
import com.alkemychallenge.alkemynazarenolaraia.auth.repository.UserRepository;
import com.alkemychallenge.alkemynazarenolaraia.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsCustomServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtTokenUtil;
    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if(userEntity == null ){
            throw new UsernameNotFoundException("user not found");
        }

        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }



    public boolean save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        String password = passwordEncoder.encode(userDTO.getPassword());
        userEntity.setPassword(password);
        userEntity = this.userRepository.save(userEntity);


        if (userEntity != null) {
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }


    public String  createToken(AuthenticationRequest authRequest) throws Exception {

        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return jwt;
    }



}
