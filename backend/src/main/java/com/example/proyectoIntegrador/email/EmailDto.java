package com.example.proyectoIntegrador.email;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Data
@Setter
@Getter
public class EmailDto {

    //String fromEmail;

    private String toEmail;

    private String subject;

    private String body;


    private Map< String, Object > model;
}