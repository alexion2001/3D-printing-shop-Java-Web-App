package com.unibuc.shop.services;

import com.unibuc.shop.exception.DuplicateException;
import com.unibuc.shop.exception.NotFoundException;
import com.unibuc.shop.model.*;
import com.unibuc.shop.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public Product create(Product product) {
        Optional<Product> existingProductSameName = productRepository.findByName(product.getName());
        existingProductSameName.ifPresent(e -> {
            throw new DuplicateException();
        });
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product)
    {
        Optional<Product> existingProduct = productRepository.findById(id.longValue());
        if(existingProduct.isPresent()){
            Product obj =  existingProduct.get();
            obj.setName(product.getName());
            obj.setPrice(product.getPrice());
            obj.setFilamentType(product.getFilamentType());
            return productRepository.save(obj);
        }else
        {
            throw new NotFoundException(id.longValue());
        }
    }


}
