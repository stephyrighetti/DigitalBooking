package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.model.Booking;
import com.example.proyectoIntegrador.model.dto.BookingDTO;

import java.util.List;
import java.util.Optional;

public interface BookingServiceInterface {

    List<BookingDTO> findBookingByUserId(Long id);
    List<BookingDTO> findBookingByProductId(Long id);
    Optional<BookingDTO> findById(Long id);
    List<BookingDTO> list();
    BookingDTO save(Booking booking);
}
