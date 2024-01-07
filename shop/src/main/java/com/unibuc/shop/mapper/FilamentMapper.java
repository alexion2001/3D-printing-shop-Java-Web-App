package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class FilamentMapper {
    public Filament filamentRequestToFilament(FilamentRequest filamentRequest) {
        return new Filament(filamentRequest.getType(),filamentRequest.getPiecesNumber());
    }

}
