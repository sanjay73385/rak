package com.pinkaura.controller;

import org.springframework.web.bind.annotation.*;
import com.pinkaura.model.User;
import com.pinkaura.repository.UserRepository;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userRepository.save(user);
    }

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody User user){
        return userRepository
                .findByEmailAndPassword(user.getEmail(), user.getPassword());
    }
}