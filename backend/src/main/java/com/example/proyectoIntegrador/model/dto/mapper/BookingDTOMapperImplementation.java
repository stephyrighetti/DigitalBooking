package com.example.proyectoIntegrador.model.dto.mapper;

import com.example.proyectoIntegrador.auth.model.dto.UserDTO;
import com.example.proyectoIntegrador.auth.model.dto.mapper.UserDTOMapperImplementacion;
import com.example.proyectoIntegrador.model.Booking;
import com.example.proyectoIntegrador.model.dto.BookingDTO;
import com.example.proyectoIntegrador.model.dto.ProductDTO;

public class BookingDTOMapperImplementation implements BookingDTOMapper {

    @Override
    public BookingDTO bookingToBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();

        UserDTOMapperImplementacion userDTOMapperImplementacion = new UserDTOMapperImplementacion();
        UserDTO userDTO = userDTOMapperImplementacion.userToUserDTO(booking.getUser());

        ProductDTOMapper productDTOMapper = new ProductDTOMapperImplementation();
        ProductDTO productDTO = productDTOMapper.productToProductDTO(booking.getProduct());


        bookingDTO.setId(booking.getId());
        bookingDTO.setBookingStartTime(booking.getBookingStartTime());
        bookingDTO.setStartDate(booking.getStartDate());
        bookingDTO.setFinalDate(booking.getFinalDate());
        bookingDTO.setProductDTO(productDTO);
        bookingDTO.setUserDTO(userDTO);

        return bookingDTO;
    }
}
