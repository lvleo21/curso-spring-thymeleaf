package com.example.curso.boot.demomvc.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity<Long>{

    @Column(name = "role")
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
