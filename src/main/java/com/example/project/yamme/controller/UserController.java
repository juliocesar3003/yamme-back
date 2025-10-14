package com.example.project.yamme.controller;

import com.example.project.yamme.model.User;
import com.example.project.yamme.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/criar")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> loginData) {
        String name = loginData.get("name");
        String senha = loginData.get("senha");
        boolean success = userService.login(name, senha);
        return success ? "Login bem-sucedido!" : "Usuário ou senha incorretos.";
    }

    @GetMapping
    public List<User> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{name}")
    public Optional<User> findByName(@PathVariable String name) {
        return userService.findByName(name);
    }
}
