package com.example.proyectoIntegrador.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageDTO {

    Long id;

    String title;

    String url;

}
