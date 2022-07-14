package com.example.proyectoIntegrador.repository;

import com.example.proyectoIntegrador.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryScore extends JpaRepository<Score,Long> {

    @Query(value="SELECT AVG(value) from scores WHERE product_id = ?1", nativeQuery = true)
    Double averageScoreByProduct(Long id);


}
