package com.example.proyectoIntegrador.auth.services;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired
    RepositoryUser userRepository;

    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetail.build(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loadUserByEmail(username);
    }

    public User updateUserCity(String city, Long id) {
        userRepository.findById(id).get().setCity(city);
       return userRepository.save(userRepository.findById(id).get());
    }


}
