package com.example.demo1.dtos;

import lombok.Data;

@Data
public class LoginResponseDto {
    private String token;
    private long expiresIn;
}
