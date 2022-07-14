package com.example.proyectoIntegrador.model;

import com.example.proyectoIntegrador.auth.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    @JsonFormat(pattern="HH:mm")
    LocalTime bookingStartTime;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate startDate;

    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDate finalDate;

    @Column(columnDefinition="double default 0.0")
    Double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="user_id", referencedColumnName = "id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIncludeProperties({"id"})
    @JoinColumn(name="product_id", referencedColumnName = "id", nullable = false)
    Product product;

}
