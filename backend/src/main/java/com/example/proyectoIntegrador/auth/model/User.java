package com.example.proyectoIntegrador.auth.model;

import com.example.proyectoIntegrador.model.Favorite;
import com.example.proyectoIntegrador.model.Score;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "users", indexes = {
        @Index(name = "uniqueUser", columnList = "email", unique = true)
})
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 200)
    private String email;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 200)
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String name;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 50)
    private String surname;

    @Column(columnDefinition="varchar(250) default '' ")
    @Size(max = 150)
    private String city;

    @Column(name = "active", columnDefinition="tinyint(1) default 0")
    private boolean active=false;


    @Column(name="hash_active")
    @Size(max = 50)
    @JsonIgnore
    private String hashActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name= "role_id", referencedColumnName = "id", nullable = false)
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Favorite> favorites = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Score> scores = new HashSet<>();


    public User() {
    }

    public User(String email, String password, String name, String surname, String city) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.hashActive= generarHashCode();
        this.active=false;
    }

    public String generarHashCode() {
        String generatedString = RandomStringUtils.randomAlphanumeric(40);
        return  generatedString;
    }

    public boolean getActive(){
        return active;
    }

}
