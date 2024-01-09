package com.unibuc.shop.repository;

import com.unibuc.shop.model.Compatibility;
import com.unibuc.shop.model.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompatibilityRepository extends JpaRepository<Compatibility, Long> {
    List<Compatibility> findByPrinterId_PrinterId(long id);
}
