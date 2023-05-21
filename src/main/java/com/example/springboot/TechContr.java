package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class TechContr {
    @Autowired
    private Repr userRepository;

    @GetMapping
    public List<AutoSpecTechnic> getUsers() {
        return userRepository.findAll();
    }
}
