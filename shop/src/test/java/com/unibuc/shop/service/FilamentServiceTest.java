package com.unibuc.shop.service;


import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Filament;
import com.unibuc.shop.repository.FilamentRepository;
import com.unibuc.shop.services.FilamentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilamentServiceTest {

    Filament filament = new Filament(1L,"pla",3);


    @InjectMocks
    private FilamentService filamentService;

    @Mock
    private FilamentRepository filamentRepository;


    @Test
    void whenFilamentDoesntExist_create_savesTheFilament() {

        // Arrange
        Filament filament = this.filament;

        Filament savedFilament = this.filament;
        when(filamentRepository.save(filament)).thenReturn(savedFilament);

        // Act
        Filament result = filamentService.create(filament);

        // Assert
        assertNotNull(result);
        assertEquals(savedFilament.getType(), result.getType());
        assertEquals(savedFilament.getPiecesNumber(), result.getPiecesNumber());
        verify(filamentRepository).save(filament);
    }

    @Test
    void whenFilamentDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(filamentRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Filament> result = filamentService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void whenFilamentExists_update() {
        // Arrange
        Filament oldFilament = this.filament;
        when(filamentRepository.findById(1L)).thenReturn(Optional.of(oldFilament));

        Filament newFilament = this.filament;
        newFilament.setPiecesNumber(5);

        when(filamentRepository.save(any(Filament.class))).thenReturn(newFilament);

        // Act
        Filament result = filamentService.updateFilament(1L, newFilament);

        // Assert
        assertNotNull(result);
        assertEquals(newFilament.getType(), result.getType());
        assertEquals(newFilament.getPiecesNumber(), result.getPiecesNumber());
        verify(filamentRepository).findById(1L);
        verify(filamentRepository).save(any(Filament.class));
    }


    @Test
    void whenFilamentExists_findById_returnsTheFilament() {
        // Arrange
        Filament filament = new Filament();
        filament.setId(1L);
        when(filamentRepository.findById(1L)).thenReturn(Optional.of(filament));

        // Act
        Optional<Filament> result = filamentService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(filament.getId(), result.get().getId());
    }

    @Test
    void whenFilamentExists_deleteById_deletesTheFilament() {
        // Arrange
        Filament filament = this.filament;
        when(filamentRepository.findById(1L)).thenReturn(Optional.of(filament));

        // Act
        assertDoesNotThrow(() -> filamentService.deleteById(1L));

        // Assert
        verify(filamentRepository).findById(1L);
        verify(filamentRepository).deleteById(1L);
    }

    @Test
    void whenFilamentDoesntExist_deleteById_throwsNotFoundException() {
        // Arrange
        when(filamentRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> filamentService.deleteById(1L));

        // Assert.
        assertEquals("The search with id 1 doesn't exist.", exception.getMessage());
        verify(filamentRepository).findById(1L);
        verify(filamentRepository, times(0)).deleteById(1L);
    }


}
