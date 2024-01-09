package com.unibuc.shop.services;

import com.unibuc.shop.dto.FilamentRequest;
import com.unibuc.shop.dto.ProductDTO;
import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrinterService {
    private final PrinterRepository printerRepository;
    private final CompatibilityRepository compatibilityRepository;

    public PrinterService(PrinterRepository printerRepository,CompatibilityRepository compatibilityRepository) {
        this.printerRepository = printerRepository;
        this.compatibilityRepository = compatibilityRepository;
    }

    public List<Printer> getAllPrinters() {
        return printerRepository.findAll();
    }

    public Optional<Printer> findById(long id) {
        return printerRepository.findById(id);
    }


    public List<FilamentRequest> findAllCompatibilitiesByPrinterId(long id) {
        return compatibilityRepository.findByPrinterId_PrinterId(id).stream()
                .map(content -> new FilamentRequest(content.getFilamentId().getType(), content.getFilamentId().getPiecesNumber()))
                .collect(Collectors.toList());
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

    public Compatibility createCompatibility(Compatibility compatibility) {
        return compatibilityRepository.save(compatibility);
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
