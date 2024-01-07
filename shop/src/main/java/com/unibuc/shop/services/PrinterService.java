package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrinterService {
    private final PrinterRepository printerRepository;

    public PrinterService(PrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
    }

    public List<Printer> getAllPrinters() {
        return printerRepository.findAll();
    }

    public Optional<Printer> findById(long id) {
        return printerRepository.findById(id);
    }

    public Optional<Printer> findByName(String name) {
        return printerRepository.findByName(name);
    }

    public Printer create(Printer printer) {
        Optional<Printer> existingPrinterSameName = printerRepository.findByName(printer.getName());
        existingPrinterSameName.ifPresent(e -> {
            throw new DuplicateException();
        });
        return printerRepository.save(printer);
    }

    public Printer updatePrinter(Long id, Printer printer)
    {
        Optional<Printer> existingPrinter = printerRepository.findById(id.longValue());
        if(existingPrinter.isPresent()){
            Printer obj =  existingPrinter.get();
            obj.setName(printer.getName());
            return printerRepository.save(obj);
        }else
        {
            throw new NotFoundException(id.longValue());
        }
    }

    public void deleteById(Long id){
        Optional<Printer> printer = printerRepository.findById(id);
        if(printer.isPresent()){
            printerRepository.deleteById(id);
        }else
        {
            throw new NotFoundException(id.longValue());
        }

    }

}
