package com.happymemories.service;

import com.happymemories.dto.UserDTO;
import com.happymemories.model.User;
import com.happymemories.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDTO saveUser(UserDTO userDTO) {
        User user = User.builder()
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .emailUser(userDTO.getEmailUser())
                .firstname(userDTO.getFirstname())
                .lastname(userDTO.getLastname())
                .roles(userDTO.getRoles())
                .build();
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO findByEmailUser(String email) {
        Optional<User> user = userRepository.findByEmailUser(email);
        UserDTO userDTO = UserDTO.builder()
                .emailUser(user.get().getEmailUser())
                .password(user.get().getPassword())
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .roles(user.get().getRoles())
                .build();
        return userDTO;
    }

    @Override
    public Set<UserDTO> findAllUsers() {
        return userRepository.findAll().stream().map(user -> UserDTO.builder()
                .password(user.getPassword())
                .emailUser(user.getEmailUser())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .roles(user.getRoles()).build())
                .collect(Collectors.toSet());
    }

    @Override
    public UserDetails getLoggedInUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

}
