package com.example.project.yamme.service;

import com.example.project.yamme.model.User;
import com.example.project.yamme.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public boolean login(String name, String senha) {
        Optional<User> userOpt = userRepository.findByName(name);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getSenha().equals(senha);
        }
        return false;
    }

    // Consultar usuário por nome
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    // Consultar todos
    public java.util.List<User> listAll() {
        return userRepository.findAll();
    }
}
