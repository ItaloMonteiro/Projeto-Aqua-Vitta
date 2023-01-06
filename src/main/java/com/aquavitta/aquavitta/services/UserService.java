package com.aquavitta.aquavitta.services;

import com.aquavitta.aquavitta.models.UserModel;
import com.aquavitta.aquavitta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserModel save(UserModel userModel){
        this.passwordEncoder = new BCryptPasswordEncoder();
        String encoder = this.passwordEncoder.encode(userModel.getPassword());
        userModel.setPassword(encoder);
        return userRepository.save(userModel);
    }

    public Optional<UserModel> findById(UUID id){
        return userRepository.findById(id);
    }
}
