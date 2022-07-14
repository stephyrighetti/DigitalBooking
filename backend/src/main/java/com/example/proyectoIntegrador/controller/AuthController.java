package com.example.proyectoIntegrador.controller;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.payload.request.LoginRequest;
import com.example.proyectoIntegrador.auth.payload.request.SignupRequest;
import com.example.proyectoIntegrador.auth.services.UserDetailServices;
import com.example.proyectoIntegrador.email.MailService;
import com.example.proyectoIntegrador.service.AuthService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/public/auth")
public class AuthController {

    @Autowired
    UserDetailServices userDetailServices;

    @Autowired
    MailService mailService;

    @Autowired
    AuthService authService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
       return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody SignupRequest signUpRequest, @RequestHeader HttpHeaders httpHeaders) {

        User user = authService.addUser(signUpRequest);
        mailService.sendEmailUser(httpHeaders.getOrigin(), user);

        return user;
    }

    @PostMapping("/signup/admin")
    public User registerUserAdmin (@Valid @RequestBody SignupRequest signupRequest, @RequestHeader HttpHeaders httpHeaders) {
        User user = authService.addUserAdmin(signupRequest);
        mailService.sendEmailUser(httpHeaders.getOrigin(), user);

        return user;
    }



    @PutMapping("/ciudad/{id}")
    public User updateCity(@PathVariable Long id, @RequestBody String city){
       return userDetailServices.updateUserCity(city, id);
    }

    @PostMapping("/activate/{hash}")
    public ResponseEntity<?> activeUser(@PathVariable String hash) throws Exception{
       return ResponseEntity.ok(mailService.activeAccount(hash));
    }

    @PostMapping("/recovery")
    public ResponseEntity<String> recoveryPassword(@RequestBody String email, @RequestHeader HttpHeaders httpHeaders){

        String host = httpHeaders.getOrigin();

        String response= mailService.recoveryPassword(email, host);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recovery-confirm/{hash}")
    public ResponseEntity<String> recoveryPasswordConfirm(@PathVariable String hash, @RequestBody String password, @RequestHeader HttpHeaders httpHeaders){

        String host = httpHeaders.getOrigin();

        String response= mailService.recoveryPasswordConfirm(hash, password, host);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate-token/{token}")
    public ResponseEntity<Boolean> validateToken(@PathVariable String token){
        boolean response= authService.validateToken(token);
        return ResponseEntity.ok(response);
    }

}
