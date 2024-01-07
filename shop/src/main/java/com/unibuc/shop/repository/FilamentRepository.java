package com.unibuc.shop.repository;

import com.unibuc.shop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FilamentRepository extends JpaRepository<Filament, Long> {
    Optional<Filament> findByType(String type);
}
