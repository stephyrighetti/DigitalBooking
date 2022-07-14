package com.example.proyectoIntegrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @NotEmpty
    String name;

    @Column(name= "short_description", nullable = false)
    @NotEmpty
    String shortDescription;

    @Column(name= "long_description", nullable = false,length=500)
    @NotEmpty
    String longDescription;

    @Column(nullable = false)
    @NotEmpty
    String address;

    @Column(nullable = false)
    @NotEmpty
    String addressAccommodation;

    @Column(nullable = false, columnDefinition="double default 0.0")
    Double average;

    @Column(nullable = false)
    Integer stars;

    @Column(nullable = false)
    String latitude;

    @Column(nullable = false)
    String longitude;

    @Column(name= "main_picture", nullable = false)
    @NotNull
    String mainPicture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="city_id", referencedColumnName = "id", nullable = false)
    City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="category_id", referencedColumnName = "id", nullable = false)
    Category category;

    @JsonIgnoreProperties({"product"})
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Policy> policies = new HashSet<>();

    @JsonIgnoreProperties({"product"})
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Image> images = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<DateNotAvailable> datesNotAvailable = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Score> scores = new HashSet<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Favorite> favorites = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "productsHasFeatures",
            joinColumns = @JoinColumn(name="products_id"),
            inverseJoinColumns = @JoinColumn(name="features_id")
    )
    Set<Feature> features = new HashSet<>();

}
