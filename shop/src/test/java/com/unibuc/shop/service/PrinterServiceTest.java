package com.unibuc.shop.service;


import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.Printer;
import com.unibuc.shop.repository.PrinterRepository;
import com.unibuc.shop.services.PrinterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrinterServiceTest {

    Printer printer = new Printer(1L,"Odis");


    @InjectMocks
    private PrinterService printerService;

    @Mock
    private PrinterRepository printerRepository;

    @Test
    void whenPrinterAlreadyExists_create_throwsDuplicateException() {
        // Arrange
        Printer printer = new Printer();
        printer.setName("Tevo");
        when(printerRepository.findByName(printer.getName()))
                .thenReturn(Optional.of(printer));

        // Act
        DuplicateException exception = assertThrows(DuplicateException.class,
                () -> printerService.create(printer));

        // Assert
        assertEquals("An entry with the same data already exists.", exception.getMessage());
        verify(printerRepository, times(0)).save(printer);

    }
    @Test
    void whenPrinterDoesntExist_create_savesThePrinter() {

        // Arrange
        Printer printer = new Printer();
        printer.setName("Tevo");

        Printer savedPrinter = this.printer;
        when(printerRepository.save(printer)).thenReturn(savedPrinter);

        // Act
        Printer result = printerService.create(printer);

        // Assert
        assertNotNull(result);
        assertEquals(savedPrinter.getPrinterId(), result.getPrinterId());
        assertEquals(savedPrinter.getName(), result.getName());
        verify(printerRepository).findByName(printer.getName());
        verify(printerRepository).save(printer);
    }


    @Test
    void whenPrinterDoesntExists_findById_returnsEmptyOptional() {
        // Arrange
        when(printerRepository.findById(1L)).thenReturn(Optional.empty());
        // Act
        Optional<Printer> result = printerService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
    @Test
    void whenPrinterExists_update() {
        // Arrange
        Printer oldPrinter = this.printer;
        when(printerRepository.findById(1L)).thenReturn(Optional.of(oldPrinter));

        Printer newPrinter = this.printer;
        newPrinter.setName("Tevo");

        when(printerRepository.save(any(Printer.class))).thenReturn(newPrinter);

        // Act
        Printer result = printerService.updatePrinter(1L, newPrinter);

        // Assert
        assertNotNull(result);
        assertEquals(newPrinter.getPrinterId(), result.getPrinterId());
        assertEquals(newPrinter.getName(), result.getName());

        verify(printerRepository).findById(1L);
        verify(printerRepository).save(any(Printer.class));
    }


    @Test
    void whenPrinterExists_findById_returnsThePrinter() {
        // Arrange
        Printer printer = new Printer();
        printer.setPrinterId(1L);
        when(printerRepository.findById(1L)).thenReturn(Optional.of(printer));

        // Act
        Optional<Printer> result = printerService.findById(1);

        // Assert
        assertNotNull(result);
        assertTrue(result.isPresent());
        assertEquals(printer.getPrinterId(), result.get().getPrinterId());
    }

    @Test
    void whenPrinterExists_deleteById_deletesThePrinter() {
        // Arrange
        Printer printer = this.printer;
        when(printerRepository.findById(1L)).thenReturn(Optional.of(printer));

        // Act
        assertDoesNotThrow(() -> printerService.deleteById(1L));

        // Assert
        verify(printerRepository).findById(1L);
        verify(printerRepository).deleteById(1L);
    }

    @Test
    void whenPrinterDoesntExist_deleteById_throwsNotFoundException() {
        // Arrange
        when(printerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> printerService.deleteById(1L));

        // Assert.
        assertEquals("The search with id 1 doesn't exist.", exception.getMessage());
        verify(printerRepository).findById(1L);
        verify(printerRepository, times(0)).deleteById(1L);
    }


}
