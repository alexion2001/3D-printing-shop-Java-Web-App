package com.unibuc.shop.repository;

import com.unibuc.shop.model.Compatibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompatibilityRepository extends JpaRepository<Compatibility, Long> {
}
