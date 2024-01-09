package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.FilamentController;
import com.unibuc.shop.dto.FilamentRequest;
import com.unibuc.shop.mapper.FilamentMapper;
import com.unibuc.shop.model.Filament;
import com.unibuc.shop.services.FilamentService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FilamentController.class)

public class FilamentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private FilamentService filamentService;
    @MockBean
    private FilamentMapper filamentMapper;

    FilamentRequest filamentRequest = new FilamentRequest("pla",3);
    Filament filament = new Filament(1L,"pla",3);

    @Test
    public void createFilament() throws Exception {
        FilamentRequest request = this.filamentRequest;

        when(filamentService.create(any())).thenReturn(this.filament);

        mockMvc.perform(post("/filament")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value(request.getType()))
                .andExpect(jsonPath("$.piecesNumber").value(request.getPiecesNumber()));

    }

    @Test
    public void updateFilament() throws Exception{

        FilamentRequest request = this.filamentRequest;
        Filament filament = this.filament;

        when(filamentService.updateFilament(any(), any())).thenReturn(filament);

        mockMvc.perform(put("/filament/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(request.getType()))
                .andExpect(jsonPath("$.piecesNumber").value(request.getPiecesNumber()));
    }
    @Test
    public void deleteFilament() throws Exception{
        filamentService.deleteById(1L);
        mockMvc.perform(delete("/filament/{id}",1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
