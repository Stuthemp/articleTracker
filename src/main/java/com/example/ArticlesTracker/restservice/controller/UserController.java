package com.example.ArticlesTracker.restservice.controller;

import com.example.ArticlesTracker.restservice.dto.UserCreationForm;
import com.example.ArticlesTracker.security.jwt.JwtUtils;
import com.example.ArticlesTracker.security.payload.request.LoginRequest;
import com.example.ArticlesTracker.security.payload.response.JwtResponse;
import com.example.ArticlesTracker.security.service.CustomUserDetails;
import com.example.ArticlesTracker.service.role.RoleService;
import com.example.ArticlesTracker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * todo Document type UserController
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private UserService userService;

    private RoleService roleService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @Autowired
    public UserController(UserService userService, RoleService roleService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public void addUser(@RequestBody UserCreationForm creationRequest ) {
        userService.createUser(creationRequest.getUsername(), creationRequest.getPassword(), creationRequest.getEmail());
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = new CustomUserDetails(userService.findByLogin(authentication.getName()));
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getUser().getId(),
            userDetails.getUsername(),
            roles,
            userDetails.getUser().getEmail()
            ));
    }
}
