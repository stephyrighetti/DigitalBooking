package com.example.proyectoIntegrador.service;
import com.example.proyectoIntegrador.model.DateNotAvailable;
import com.example.proyectoIntegrador.model.dto.DateNotAvailableDTO;
import java.util.List;
import java.util.Optional;

public interface DateNotAvailableServiceInterface {

    Optional<DateNotAvailableDTO> findById(Long id);
    Optional<DateNotAvailable> delete(Long id);
    List<DateNotAvailableDTO> list();
    DateNotAvailableDTO save(DateNotAvailable dateNotAvailable);

}
