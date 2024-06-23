package com.happymemories.service;

import com.happymemories.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public interface UserService {
    UserDTO saveUser(UserDTO user);
    UserDTO findByEmailUser(String email);
    Set<UserDTO> findAllUsers();
    UserDetails getLoggedInUserDetails();
}
