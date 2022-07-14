package com.example.proyectoIntegrador.service;

import com.example.proyectoIntegrador.auth.model.User;
import com.example.proyectoIntegrador.auth.repository.RepositoryUser;
import com.example.proyectoIntegrador.exception.RecordNotFoundException;
import com.example.proyectoIntegrador.model.Booking;
import com.example.proyectoIntegrador.model.DateNotAvailable;
import com.example.proyectoIntegrador.model.dto.BookingDTO;
import com.example.proyectoIntegrador.model.dto.mapper.BookingDTOMapperImplementation;
import com.example.proyectoIntegrador.repository.RepositoryBooking;
import com.example.proyectoIntegrador.repository.RepositoryProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInterface{

    @Autowired
    RepositoryBooking repository;

    @Autowired
    RepositoryUser repositoryUser;

    @Autowired
    RepositoryProduct repositoryProduct;

    @Override
    public List<BookingDTO> findBookingByUserId(Long id) {
        List<Booking> listBooking = repository.findBookingByUserId(id);

        BookingDTOMapperImplementation bookingDTOMapperImplementation = new BookingDTOMapperImplementation();

        List<BookingDTO> bookingDTOList = new ArrayList<>();

        for (Booking booking : listBooking) {
            bookingDTOList.add(bookingDTOMapperImplementation.bookingToBookingDTO(booking));
        }
        return bookingDTOList;
    }

    @Override
    public List<BookingDTO> findBookingByProductId(Long id) {

        List<Booking> listBooking = repository.findBookingByProductId(id);

        BookingDTOMapperImplementation bookingDTOMapperImplementation = new BookingDTOMapperImplementation();

        List<BookingDTO> bookingDTOList = new ArrayList<>();

        for (Booking booking : listBooking) {
            bookingDTOList.add(bookingDTOMapperImplementation.bookingToBookingDTO(booking));
        }
        return bookingDTOList;
    }

    @Override
    public Optional<BookingDTO> findById(Long id) {
        Optional<Booking> searchedBooking = repository.findById(id);

        BookingDTOMapperImplementation bookingDTOMapperImplementation = new BookingDTOMapperImplementation();

        BookingDTO bookingDTO = bookingDTOMapperImplementation.bookingToBookingDTO(searchedBooking.get());
        Optional<BookingDTO> optionalBookingDTO = Optional.of(bookingDTO);

        if (optionalBookingDTO.isPresent()){
            return optionalBookingDTO;
        }else{
            throw new RecordNotFoundException("Booking con id "+id.toString()+" no existe !");
        }

    }

    @Override
    public List<BookingDTO> list() {
        List<Booking> bookingList = repository.findAll();
        BookingDTOMapperImplementation bookingDTOMapperImplementation = new BookingDTOMapperImplementation();
        List<BookingDTO> bookingDTOList = new ArrayList<>();

        for (Booking booking : bookingList) {
            bookingDTOList.add(bookingDTOMapperImplementation.bookingToBookingDTO(booking));
        }

        return bookingDTOList;

    }


    @Override
    public BookingDTO save(Booking booking) {

        LocalDate finalDate = booking.getFinalDate();
        LocalDate startDate = booking.getStartDate();
        LocalDate aux = booking.getStartDate();

        ArrayList<LocalDate> interval = new ArrayList<>();

        if(finalDate.isAfter(aux)){
            aux = aux.plusDays(1L);
            interval.add(aux);
        }
        interval.add(finalDate);
        interval.add(startDate);

        for (LocalDate date :interval) {
            for (DateNotAvailable dateNotAvailable : repositoryProduct.findById(booking.getProduct().getId()).get().getDatesNotAvailable()) {
                if(date.equals(dateNotAvailable.getDateNotAvailable())){
                    throw new RecordNotFoundException("YA HAY UNA RESERVA CON ESTA FECHA");
                }
            }
        }
        for (LocalDate date :interval){
            DateNotAvailable dateNotAvailable1 = new DateNotAvailable();
            dateNotAvailable1.setDateNotAvailable(date);
            dateNotAvailable1.setProduct(booking.getProduct());
            repositoryProduct.findById(booking.getProduct().getId()).get().getDatesNotAvailable().add(dateNotAvailable1);
        }


        User user = repositoryUser.findById(booking.getUser().getId()).get();

        user.setCity(booking.getUser().getCity());
        repositoryUser.save(user);

        booking.setProduct(repositoryProduct.findById(booking.getProduct().getId()).get());
        booking.setUser(repositoryUser.findById(booking.getUser().getId()).get());


        Booking bookingSaved = repository.save(booking);
        BookingDTOMapperImplementation bookingDTOMapperImplementation = new BookingDTOMapperImplementation();
        BookingDTO bookingDTO = bookingDTOMapperImplementation.bookingToBookingDTO(bookingSaved);

        return bookingDTO;

    }

}
