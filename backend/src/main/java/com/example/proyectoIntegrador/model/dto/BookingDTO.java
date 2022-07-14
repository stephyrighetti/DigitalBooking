package com.example.proyectoIntegrador.model.dto;

import com.example.proyectoIntegrador.auth.model.dto.UserDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingDTO {

    Long id;

    LocalTime bookingStartTime;

    LocalDate startDate;

    LocalDate finalDate;

    UserDTO userDTO;

    ProductDTO productDTO;

}
