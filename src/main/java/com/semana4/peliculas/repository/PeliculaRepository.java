package com.semana4.peliculas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.semana4.peliculas.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
}
