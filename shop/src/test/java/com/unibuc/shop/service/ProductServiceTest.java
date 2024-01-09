package com.unibuc.shop.service;


import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Filament;
import com.unibuc.shop.model.Product;
import com.unibuc.shop.repository.ProductRepository;
import com.unibuc.shop.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    Product product = new Product(1L,"lampa",25, new Filament(1,"pla",5));


    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void whenProductAlreadyExists_create_throwsDuplicateException() {
        // Arrange
        Product product = new Product();
        product.setName("lampa");
        when(productRepository.findByName(product.getName()))
                .thenReturn(Optional.of(product));

        // Act
        DuplicateException exception = assertThrows(DuplicateException.class,
                () -> productService.create(product));

        // Assert
        assertEquals("An entry with the same data already exists.", exception.getMessage());
        verify(productRepository, times(0)).save(product);

    }
    @Test
    void whenProductDoesntExist_create_savesTheProduct() {

        // Arrange
        Product product = this.product;
        product.setName("cub");

        Product savedProduct = this.product;
        when(productRepository.save(product)).thenReturn(savedProduct);

        // Act
        Product result = productService.create(product);

        // Assert
        assertNotNull(result);
        assertEquals(savedProduct.getProductId(), result.getProductId());
        assertEquals(savedProduct.getName(), result.getName());
        assertEquals(product.getPrice(), result.getPrice());
        verify(productRepository).findByName(product.getName());
        verify(productRepository).save(product);
    }

    @Test
    void whenProductDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Product> result = productService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void whenProductExists_update() {
        // Arrange
        Product oldProduct = this.product;
        when(productRepository.findById(1L)).thenReturn(Optional.of(oldProduct));

        Product newProduct = this.product;
        newProduct.setPrice(250);

        when(productRepository.save(any(Product.class))).thenReturn(newProduct);

        // Act
        Product result = productService.updateProduct(1L, newProduct);

        // Assert
        assertNotNull(result);
        assertEquals(newProduct.getProductId(), result.getProductId());
        assertEquals(newProduct.getName(), result.getName());
        assertEquals(newProduct.getPrice(), result.getPrice());
        assertEquals(newProduct.getFilamentType(), result.getFilamentType());

        verify(productRepository).findById(1L);
        verify(productRepository).save(any(Product.class));
    }


    @Test
    void whenProductExists_findById_returnsTheProduct() {
        // Arrange
        Product product = new Product();
        product.setProductId(1L);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(product.getProductId(), result.get().getProductId());
    }

    @Test
    void whenProductExists_deleteById_deletesTheProduct() {
        // Arrange
        Product product = this.product;
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        assertDoesNotThrow(() -> productService.deleteById(1L));

        // Assert
        verify(productRepository).findById(1L);
        verify(productRepository).deleteById(1L);
    }

    @Test
    void whenProductDoesntExist_deleteById_throwsNotFoundException() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> productService.deleteById(1L));

        // Assert.
        assertEquals("The search with id 1 doesn't exist.", exception.getMessage());
        verify(productRepository).findById(1L);
        verify(productRepository, times(0)).deleteById(1L);
    }


}
