package com.example.proyectoIntegrador.model;


import com.example.proyectoIntegrador.auth.model.User;
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
@Table(name="scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    Double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIncludeProperties({"id"})
    @JoinColumn(name= "product_id", referencedColumnName = "id")
    Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIncludeProperties({"id"})
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    User user;

}
