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

10


/* O papel do que chamamos de Controllers é de orquestração dos dados recebidos pela sua aplicação Java. 
Ele deve tratar as entradas do sistema com algumas verificações, como de tipos de dados, por exemplo, se um parâmetro obrigatório foi enviado na requisição. */
@RestController
// endereço da URI que a aplicação acessa para usar esse controller
@RequestMapping("/user")
@Validated
public class UserController {
    
    // anotação para inicializar construtor
    @Autowired
    private UserService userService;

    // localhost:8080/user
    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> user = this.userService.getAll();

        return ResponseEntity.ok().body(user);
    }

    // localhost:8080/user/1
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    // localhost:8080/user
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

    // localhost:8080/user/1
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();          
    }

    // localhost:8080/user/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();          
    }

}
