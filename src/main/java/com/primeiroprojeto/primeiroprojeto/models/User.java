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

@Entity
@Table(name = "user")
public class User {
    
    public User() {}

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) 
            return true;

        if(obj == null) 
            return false;
            
        if (!(obj instanceof User)) 
            return false;

        User other = (User) obj;

        if (this.id == null)
            if (other.id != null) 
                return false;
            else if(!this.id.equals(other.id))
                return false;

        return Objects.equals(this.id, other.id) && 
               Objects.equals(this.username, other.username) && 
               Objects.equals(this.password, other.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;
    
    @Column(name = "username", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 100)
    private String username;

    @JsonProperty(access = Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotNull
    @NotEmpty
    @Size(min = 8, max = 60)
    private String password;
}
