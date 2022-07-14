package com.example.proyectoIntegrador.controller;


import com.example.proyectoIntegrador.model.Booking;
import com.example.proyectoIntegrador.model.dto.BookingDTO;
import com.example.proyectoIntegrador.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/private/bookings")
public class BookingController {

    @Autowired
    BookingService service;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<BookingDTO>> findBookingByProductId(@PathVariable Long id){
        return ResponseEntity.ok(service.findBookingByProductId(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingDTO>> findBookingByUserId(@PathVariable Long id){
        return ResponseEntity.ok(service.findBookingByUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<BookingDTO>> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<BookingDTO>> list(){
        return ResponseEntity.ok(service.list());
    }

    @PostMapping
    public ResponseEntity<BookingDTO> save(@RequestBody Booking booking){
        return ResponseEntity.ok(service.save(booking));
    }


}
