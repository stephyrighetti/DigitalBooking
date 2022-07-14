package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    @NotEmpty
    String name;


    @JsonIgnore
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    Set<City> cities = new HashSet<>();

   public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
