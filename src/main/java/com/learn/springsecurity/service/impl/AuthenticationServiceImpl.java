package com.learn.springsecurity.service.impl;

import static com.learn.springsecurity.enumerated.TokenType.BEARER;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.springsecurity.dto.request.LoginRequest;
import com.learn.springsecurity.dto.request.RegisterRequest;
import com.learn.springsecurity.dto.response.LoginResponse;
import com.learn.springsecurity.dto.response.RegisterResponse;
import com.learn.springsecurity.dto.response.UserResponse;
import com.learn.springsecurity.enumerated.Role;
import com.learn.springsecurity.model.Token;
import com.learn.springsecurity.model.User;
import com.learn.springsecurity.repository.TokenRepository;
import com.learn.springsecurity.repository.UserRepository;
import com.learn.springsecurity.service.AuthenticationService;
import com.learn.springsecurity.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .build();
        userRepository.save(user);
        return RegisterResponse.builder()
                .message("User registered successfully")
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Authenticate user credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Retrieve the user from the database
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
                

        // Generate JWT token with user claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().toString());
        var accessToken = jwtUtil.generateToken(claims, user);

        // Revoke existing tokens and save the new one
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        // Return login response with user ID and token
        return LoginResponse.builder()
                .message("Logged in successfully.")
                .accessToken(accessToken)
                .userId(user.getId())  
                .role(user.getRole())
                .build();
    }

    private void saveUserToken(User user, String accessToken) {
        var token = Token.builder()
                .user(user)
                .token(accessToken)
                .tokenType(BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
public UserResponse getUserByEmail(String email) {
        var use = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("User not found"));
//     return LoginResponse.builder()
//                 .message("Logged in successfully.")
//                 .accessToken(accessToken)
//                 .userId(user.getId())  
//                 .role(user.getRole())
//                 .build();
    return UserResponse.builder()
                .id(use.getId())
                .email(use.getEmail())
                .name(use.getName())
                .build();
                        
}


    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
    
    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (jwtUtil.isTokenValid(refreshToken, user)) {
                var accessToken = jwtUtil.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = LoginResponse.builder()
                        .message("New access token generated successfully.")
                        .accessToken(accessToken)
                        .userId(user.getId())  // Include user ID in the response
                        .role(user.getRole())
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
