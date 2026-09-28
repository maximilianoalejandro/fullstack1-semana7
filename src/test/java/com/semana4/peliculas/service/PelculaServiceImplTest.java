package com.semana4.peliculas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.semana4.peliculas.model.Pelicula;
import com.semana4.peliculas.repository.PeliculaRepository;  


@ExtendWith(MockitoExtension.class)
public class PelculaServiceImplTest {
    
    @Mock
    private PeliculaRepository repository;

    @InjectMocks
    private PeliculaServiceImpl service;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Inception");
        pelicula.setAno(2010);
        pelicula.setDirector("Christopher Nolan");
        pelicula.setGenero("Sci-Fi");
        pelicula.setSinopsis("Espacio exterior");
    }


    @Test
    void testgetAllPeliculas() {
        List<Pelicula> expected = Arrays.asList(pelicula);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.getAllPeliculas());
    }

    @Test
    void testgetPeliculaById() {
        when(repository.findById(1L)).thenReturn(Optional.of(pelicula));
        assertEquals(Optional.of(pelicula), service.getPeliculaById(1L));
    }

    @Test
    void testCreatePelicula() {
        when(repository.save(pelicula)).thenReturn(pelicula);
        assertEquals(pelicula, service.createPelicula(pelicula));
    }

    @Test
    void testUpdatePeliculaExists() {
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(pelicula)).thenReturn(pelicula);
        Pelicula result = service.updatePelicula(1L, pelicula);
        assertEquals(1L, pelicula.getId());
        assertEquals(pelicula, result);
        verify(repository).save(pelicula);
    }

    @Test
    void testUpdatePeliculaNotExists() {
        when(repository.existsById(1L)).thenReturn(false);
        assertNull(service.updatePelicula(1L, pelicula));
        verify(repository, never()).save(any());
    }

    @Test
    void testDeletePelicula() {
        service.deletePelicula(1L);
        verify(repository).deleteById(1L);
    }

    @AfterEach
    void tearDown() {
        pelicula = null;
    }
}
