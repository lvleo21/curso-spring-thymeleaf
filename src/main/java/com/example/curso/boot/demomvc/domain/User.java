package com.example.curso.boot.demomvc.domain;




import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends AbstractEntity<Long>{

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 5, message = "*Your user name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

//    @Length(min = 6, max = 11, message = "Sua senha deve conter entre {min} e {max} caracteres.")
    @Column(name = "password", nullable = false)
//    @Pattern(regexp = "^[A-Z]+[a-z]+\\d+$",
//            message = "* Sua senha deve possuir pelo menos uma letra maiúscula; <br> " +
//                      "* Sua senha deve possuir pelo menos uma letra minúscula;<br> " +
//                      "* Sua senha não pode conter caracteres especiais;"
//    )
    private String password;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name", nullable = false)
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }



    public Set<Role> getRoles() {
        for (Role role: this.roles) {
            System.out.println(role.getRole());
        }
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
