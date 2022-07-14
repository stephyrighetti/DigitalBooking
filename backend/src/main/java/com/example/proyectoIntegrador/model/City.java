package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Entity
@Table(name="cities")
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(nullable = false)
    @NotEmpty
    String name;


    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", referencedColumnName = "id", nullable = false)
    Country country;


    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    @JsonIgnore
    Set<Product> products = new HashSet<>();


  public City(Long id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

}
