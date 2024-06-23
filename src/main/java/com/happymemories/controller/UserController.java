package com.happymemories.controller;

import com.happymemories.dto.UserDTO;
import com.happymemories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users/happy-memories")
@CrossOrigin(originPatterns = {"http://localhost:3000"})
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getHelloMessage() {
        return "User service up and running";
    }

    @PostMapping("/signup")
    public UserDTO saveUsers(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @GetMapping("all-users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/logged-in-user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<UserDTO> getLoggedInUser() {
        return ResponseEntity.ok(userService.findByEmailUser(userService.getLoggedInUserDetails().getUsername()));
    }

}
