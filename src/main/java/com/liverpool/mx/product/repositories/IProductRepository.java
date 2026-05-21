package com.liverpool.mx.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liverpool.mx.product.models.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, String> {

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String keyword, String keywordDescription);

}
