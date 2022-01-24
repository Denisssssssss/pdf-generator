package com.example.pdfgeneratorclient.services;

import com.example.pdfgeneratorclient.models.User;
import com.example.pdfgeneratorclient.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(String username, String password) {
        if (!userRepository.findByUsername(username).isPresent()) {
            return userRepository.save(new User(username, passwordEncoder.encode(password)));
        }
        throw new IllegalArgumentException();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }
}
