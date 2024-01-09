package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.PrinterController;
import com.unibuc.shop.dto.PrinterRequest;
import com.unibuc.shop.mapper.CompatibilityMapper;
import com.unibuc.shop.mapper.PrinterMapper;
import com.unibuc.shop.model.Compatibility;
import com.unibuc.shop.model.Printer;
import com.unibuc.shop.services.PrinterService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PrinterController.class)

public class PrinterControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PrinterService printerService;
    @MockBean
    private PrinterMapper printerMapper;
    @MockBean
    private CompatibilityMapper compatibilityMapper;

    PrinterRequest printerRequest = new PrinterRequest( "Odis");
    Printer printer = new Printer(1L,"Odis");

    @Test
    public void createPrinter() throws Exception {
        PrinterRequest request = this.printerRequest;

        when(printerService.create(any())).thenReturn(this.printer);

        mockMvc.perform(post("/printer/printer")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()));

    }

    @Test
    public void updatePrinter() throws Exception{

        PrinterRequest request = this.printerRequest;
        Printer printer = this.printer;

        when(printerService.updatePrinter(any(), any())).thenReturn(printer);

        mockMvc.perform(put("/printer/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()));
    }
    @Test
    public void deletePrinter() throws Exception{
        printerService.deleteById(1L);
        mockMvc.perform(delete("/printer/{id}",1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
