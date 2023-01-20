package com.primeiroprojeto.primeiroprojeto.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/*
  A anotação @Entity é utilizada para informar que uma classe também é uma entidade. A partir disso, a JPA estabelecerá a ligação entre 
  a entidade e uma tabela de mesmo nome no banco de dados, onde os dados de objetos desse tipo poderão ser persistidos.
*/
@Entity
@Table(name = "user")
public class User {
    
    // Construtores
    // "construtores são os responsáveis por criar o objeto em memória, ou seja, instanciar a classe que foi definida"
    public User() {}
    
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters e setters
    /* "Getters e setters são usados para proteger seus dados, especialmente na criação de classes.
       Para cada instância de variável, um método getter retorna seu valor, enquanto um método setter o define ou atualiza." */
    
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Anotação de identificação
    @Id
    // Anotação indicando que é no banco de dados esse campo é do tipo auto gerável
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // definição das colunas para criação de tabela no banco de dados
    // nome da coluna / se o dado é unico, não permitindo duplicatas
    @Column(name = "id", unique = true)
    private Long id;
    
    // definição das colunas para criação de tabela no banco de dados
    // nome da coluna / tamanho limite de caracteres / se pode ser nula
    @Column(name = "username", length = 100, nullable = false)
    
    // validação que não permite dado nulo, não permite dado vazio e o tamanho minimo é 2 e maximo 100 caracteres
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    // anotação para indicar que esse dado é apenas para escrita e não retornada quando os dados são listados
    @JsonProperty(access = Access.WRITE_ONLY) 
    
    // definição das colunas para criação de tabela no banco de dados
    // nome da coluna / tamanho limite de caracteres / se pode ser nula
    @Column(name = "password", length = 60, nullable = false) 
    
    // validação que não permite dado nulo, não permite dado vazio e o tamanho minimo é 8 e maximo 60 caracteres
    @NotNull 
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;
}
