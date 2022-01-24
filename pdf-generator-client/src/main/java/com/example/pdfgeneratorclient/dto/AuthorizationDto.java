package com.example.pdfgeneratorclient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationDto {

    private final String username;
    private final String password;
}
