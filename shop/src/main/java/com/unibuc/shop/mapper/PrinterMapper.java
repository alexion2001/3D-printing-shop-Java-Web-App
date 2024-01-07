package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.*;
import com.unibuc.shop.model.*;
import org.springframework.stereotype.Component;

@Component
public class PrinterMapper {
    public Printer printerRequestToPrinter(PrinterRequest printerRequest) {
        return new Printer(printerRequest.getName());
    }

}
