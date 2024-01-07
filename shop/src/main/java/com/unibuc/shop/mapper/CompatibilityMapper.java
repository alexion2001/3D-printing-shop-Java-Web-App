package com.unibuc.shop.mapper;

import com.unibuc.shop.dto.CompatibilityRequest;
import com.unibuc.shop.model.Compatibility;


public class CompatibilityMapper {
    public Compatibility compatibilityRequestToCompatibility(CompatibilityRequest compatibilityRequest) {
        return new Compatibility(compatibilityRequest.getFilamentId(),compatibilityRequest.getPrinterId());
    }
}
