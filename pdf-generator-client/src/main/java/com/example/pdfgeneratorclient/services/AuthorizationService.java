package com.example.pdfgeneratorclient.services;

import com.example.pdfgeneratorclient.dto.TokenDto;

public interface AuthorizationService {

    TokenDto signUp(String username, String password);

    TokenDto signIn(String username, String password);
}
