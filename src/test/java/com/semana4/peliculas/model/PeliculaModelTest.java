package com.semana4.peliculas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
 

 
public class PeliculaModelTest {
   @Test 
    void testGetterAndSetters() {
        Pelicula  pelicula = new Pelicula();
     
        pelicula.setId(1L);
        pelicula.setTitulo("Inception");
        pelicula.setAno(2010);
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Sci-Fi");
        pelicula.setSinopsis("Espacio exterior");

        assertEquals(1L, pelicula.getId());
        assertEquals("Inception", pelicula.getTitulo());
        assertEquals(2010, pelicula.getAno());
        assertEquals("Christopher Nolan", pelicula.getDirector());
        assertEquals("Sci-Fi", pelicula.getGenero());
        assertEquals("Espacio exterior",pelicula.getSinopsis()
        
        );
    }
}