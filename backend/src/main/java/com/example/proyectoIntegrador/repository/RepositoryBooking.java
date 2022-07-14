package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryBooking extends JpaRepository<Booking,Long> {

    List<Booking> findBookingByProductId(Long id);

    List<Booking> findBookingByUserId(Long id);
}
