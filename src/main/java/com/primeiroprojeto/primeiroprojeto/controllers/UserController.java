package com.primeiroprojeto.primeiroprojeto.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.primeiroprojeto.primeiroprojeto.models.User;
import com.primeiroprojeto.primeiroprojeto.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> user = this.userService.getAll();

        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody User user) {
        this.userService.create(user);
        URI uri = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();

        return ResponseEntity.created(uri).build();          
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();          
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();          
    }

}
