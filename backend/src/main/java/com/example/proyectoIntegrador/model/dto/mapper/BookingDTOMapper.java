package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.model.Booking;
import com.example.proyectoIntegrador.model.dto.BookingDTO;

public interface BookingDTOMapper {

    BookingDTO bookingToBookingDTO(Booking booking);
}
