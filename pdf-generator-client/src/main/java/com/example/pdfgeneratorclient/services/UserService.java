package com.example.pdfgeneratorclient.services;

import com.example.pdfgeneratorclient.models.User;

public interface UserService {

    User save(String username, String password);

    User findById(Long id);

    User findByUsername(String username);
}
