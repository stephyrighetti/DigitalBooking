package com.example.proyectoIntegrador.auth.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Setter
@Getter
public class SignupRequest {


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 200)
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    private String city;


}
