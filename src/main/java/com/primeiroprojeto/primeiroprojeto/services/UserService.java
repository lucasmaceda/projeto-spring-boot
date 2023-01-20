package com.primeiroprojeto.primeiroprojeto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primeiroprojeto.primeiroprojeto.models.User;
import com.primeiroprojeto.primeiroprojeto.repositories.UserRepository;

/*
   Os Services são responsáveis pela lógica de negócio da sua aplicação, além de ser responsável por se comunicar 
   com as camadas mais internas do Software, como por exemplo, uma camada de Dados.
*/
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // listar todos os dados
    public List<User> getAll() {
        List<User> user = this.userRepository.findAll();
        
        return user;
    }

    // Buscar por ID
    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id); 

        return user.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado ! id: " + id + ", Tipo: " + User.class.getName()
        ));
    }

    // Cadastrar Usuário
    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    // Editar Usuário
    @Transactional
    public User update(User obj) {
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        newObj.setUsername(obj.getUsername());
        return this.userRepository.save(newObj);
    }

    // Excluir usuário
    public void delete(Long id) {
        findById(id);

        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir.");
        }
    }

}
