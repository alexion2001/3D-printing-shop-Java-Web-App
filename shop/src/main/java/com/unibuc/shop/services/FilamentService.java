package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilamentService {
    private final FilamentRepository filamentRepository;

    public FilamentService(FilamentRepository filamentRepository) {
        this.filamentRepository = filamentRepository;
    }

    public List<Filament> getAllFilaments() {
        return filamentRepository.findAll();
    }

    public Optional<Filament> findById(long id) {
        return filamentRepository.findById(id);
    }

    public List<Filament> findByType(String type) {
        return filamentRepository.findByType(type);
    }

    public Filament create(Filament filament) {

        return filamentRepository.save(filament);
    }

    public Filament updateFilament(Long id, Filament filament)
    {
        Optional<Filament> existingFilament = filamentRepository.findById(id.longValue());
        if(existingFilament.isPresent()){
            Filament obj =  existingFilament.get();
            obj.setType(filament.getType());
            obj.setPiecesNumber(filament.getPiecesNumber());
            return filamentRepository.save(obj);
        }else
        {
            throw new NotFoundException(id.longValue());
        }
    }
    public void deleteById(Long id){
        Optional<Filament> filament = filamentRepository.findById(id);
        if(filament.isPresent()){
            filamentRepository.deleteById(id);
        }else
        {
            throw new NotFoundException(id.longValue());
        }

    }

}
