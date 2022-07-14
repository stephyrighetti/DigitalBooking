package com.example.proyectoIntegrador.model;

import com.example.proyectoIntegrador.auth.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name="favorites")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIncludeProperties({"id"})
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIncludeProperties({"id"})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    Product product;

}
