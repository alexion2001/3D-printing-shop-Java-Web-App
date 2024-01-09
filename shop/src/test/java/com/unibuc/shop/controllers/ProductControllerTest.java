package com.unibuc.shop.controllers;

import com.fasterxml.jackson.databind.*;

import com.unibuc.shop.controller.ProductController;
import com.unibuc.shop.dto.ProductRequest;
import com.unibuc.shop.mapper.ProductMapper;
import com.unibuc.shop.model.Filament;
import com.unibuc.shop.model.Product;
import com.unibuc.shop.services.ProductService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductController.class)

public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;
    @MockBean
    private ProductMapper productMapper;

    ProductRequest productRequest = new ProductRequest( "lampa",25, new Filament(1,"pla",5));
    Product product = new Product(1L,"lampa",25, new Filament(1,"pla",5));

    @Test
    public void createProduct() throws Exception {
        ProductRequest request = this.productRequest;

        when(productService.create(any())).thenReturn(this.product);

        mockMvc.perform(post("/product")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.price").value(request.getPrice()));

    }

    @Test
    public void updateProduct() throws Exception{

        ProductRequest request = this.productRequest;
        Product product = this.product;

        when(productService.updateProduct(any(), any())).thenReturn(product);

        mockMvc.perform(put("/product/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.getName()))
                .andExpect(jsonPath("$.price").value(request.getPrice()));
    }
    @Test
    public void deleteProduct() throws Exception{
        productService.deleteById(1L);
        mockMvc.perform(delete("/product/{id}",1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }
}
