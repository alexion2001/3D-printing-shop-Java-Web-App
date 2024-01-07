package com.unibuc.shop.controller;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.mapper.*;
import com.unibuc.shop.model.*;
import com.unibuc.shop.services.*;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController()
@Validated
@RequestMapping("/product")
@Api(value = "/products",
        tags = "Products",
        description = "Controller for managing products data")
public class ProductController {

    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    @ApiOperation(value = "Dispaly all products",
            notes = "Displays all products together with all related data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public List<Product> getAllProducts() {
        return  productService.getAllProducts();

    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find product by id",
            notes = "Dispaly product based on the id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Product> getProductById(long id) {
        return  productService.findById(id);
    }


    @GetMapping(path = "/{name}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Find product by name",
            notes = "Dispaly product based on the name")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully displayed"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public Optional<Product> getProductByName(String name) {
        return  productService.findByName(name);
    }


    @PostMapping
    @ApiOperation(value = "Create a Product",
            notes = "Creates a new Product based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully created based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Product> createProduct(
            @RequestBody
            @ApiParam(name = "product", value = "Product details", required = true)
                    ProductRequest productRequest) {
        Product savedEntry = productService.create(
                productMapper.productRequestToProduct(productRequest));
        return ResponseEntity
                .created(URI.create("/product/" + savedEntry.getProductId()))
                .body(savedEntry);
    }


    @PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Update a Product data",
            notes = "Update an existing Product data based on the information received in the request")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "The entry was successfully updated based on the received request"),
            @ApiResponse(code = 400, message = "Validation error on the received request")
    })
    public ResponseEntity<Product> updateProduct(
            @PathVariable @Parameter(name = "id",description = "Product id",example = "1",required = true) Long id,
            @Valid
            @RequestBody
            @ApiParam(name = "product", value = "Product details", required = true)
                    ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        return  ResponseEntity.ok(productService.updateProduct(id,product));
    }
}
