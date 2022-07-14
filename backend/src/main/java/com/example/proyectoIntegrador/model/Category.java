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
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    @NotEmpty
    String title;

    @Column(nullable = false)
    @NotEmpty
    String description;

    @Column(name= "url_image", nullable = false)
    @NotEmpty
    String urlImage;

    @Column(name= "quantity_products", columnDefinition = "integer default 0")
    Integer quantityProducts;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Product> products = new HashSet<>();



    public Category(String title, String description, String urlImage) {
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
    }

    public Category(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
}

