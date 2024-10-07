package com.learn.springsecurity.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.learn.springsecurity.enumerated.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String userId; // Include user ID in the login response
    @JsonProperty("access_token")
    private String accessToken;
    private Role role;
}
