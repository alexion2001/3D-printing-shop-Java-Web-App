package com.unibuc.shop.repository;

import com.unibuc.shop.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findProductsByOrderId_OrderId(long id);
}
