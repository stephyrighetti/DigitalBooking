package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.auth.jwt.JwtUtils;
import com.example.proyectoIntegrador.auth.model.ERole;
import com.example.proyectoIntegrador.auth.model.Role;
import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.payload.request.LoginRequest;
import com.example.proyectoIntegrador.auth.payload.request.SignupRequest;
import com.example.proyectoIntegrador.auth.payload.response.JwtResponse;
import com.example.proyectoIntegrador.auth.repository.RepositoryRole;
import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import com.example.proyectoIntegrador.auth.services.UserDetail;
import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RepositoryRole roleRepository;

    @Autowired
    RepositoryUser userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    public JwtResponse login(LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getName(),
                userDetails.getSurname(),
                userDetails.getCity(),
                roles,
                userDetails.getFavorites());
    }

    public JwtResponse loginSinPassword(User user){

        UserDetail userDetail = UserDetail.build(user);
        String jwt = jwtUtils.generateJwtTokenSinPassword(userDetail);

        List<String> roles = userDetail.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetail.getId(),
                userDetail.getEmail(),
                userDetail.getName(),
                userDetail.getSurname(),
                userDetail.getCity(),
                roles,
                userDetail.getFavorites());
    }

    public User addUser(SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RecordNotFoundException("Usuario con el email '" + signUpRequest.getEmail() + "' ya existe, use la opcion de recuperar contraseña!");
        }

        Role userRole = roleRepository.findByName(ERole.USER);

        if (userRole == null){
            Role role = new Role();
            role.setName(ERole.USER);
            roleRepository.save(role);
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getCity());

        user.setRole(userRole);
        userRepository.save(user);

        return user;
    }

    public User addUserAdmin(SignupRequest signUpRequest){
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RecordNotFoundException("Usuario con el email '" + signUpRequest.getEmail() + "' ya existe, use la opcion de recuperar contraseña!");
        }

        Role userRole = roleRepository.findByName(ERole.ADMIN);

        if (userRole == null){
            Role role = new Role();
            role.setName(ERole.ADMIN);
            roleRepository.save(role);
        }
        // Create new user's account
        User user = new User(signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getName(),
                signUpRequest.getSurname(),
                signUpRequest.getCity());

        user.setRole(userRole);
        userRepository.save(user);

        return user;
    }

    public boolean validateToken(String token){
        if(jwtUtils.validateJwtToken(token)){
            return jwtUtils.validateJwtToken(token);
        }else{
            throw new RecordNotFoundException("Token invalido: '" + token + "', vuelva a loguearse!");
        }


    }

}
