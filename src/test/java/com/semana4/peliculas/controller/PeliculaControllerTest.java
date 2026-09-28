package com.semana4.peliculas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import java.util.Arrays;
import java.util.Optional;

import com.semana4.peliculas.model.Pelicula;
import com.semana4.peliculas.service.PeliculaService;

import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

  

@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaService service;

    @Autowired
    private ObjectMapper mapper;

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
    void testGetAllPeliculas() throws Exception {
        when(service.getAllPeliculas()).thenReturn(Arrays.asList(pelicula));
        mockMvc.perform(get("/peliculas"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(Arrays.asList(pelicula))));
    }


    @Test
    void testGetPeliculaById() throws Exception {
        when(service.getPeliculaById(1L)).thenReturn(Optional.of(pelicula));
        mockMvc.perform(get("/peliculas/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pelicula)));
    }


    @Test
    void testCreatePelicula() throws Exception {
        when(service.createPelicula(any(Pelicula.class))).thenReturn(pelicula);
        mockMvc.perform(post("/peliculas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(pelicula)))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(pelicula)));
    }

    @Test
    void testDeletePelicula() throws Exception {
        mockMvc.perform(delete("/peliculas/1"))
                .andExpect(status().isOk());
        verify(service).deletePelicula(1L);
    }

    @AfterEach
    void tearDown() {
        pelicula = null;
    }

}
