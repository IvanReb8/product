package com.liverpool.mx.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.liverpool.mx.product.clients.InventoryFeignClient;
import com.liverpool.mx.product.exceptions.BadRequestException;
import com.liverpool.mx.product.mappers.ProductMapper;
import com.liverpool.mx.product.models.InventoryDto;
import com.liverpool.mx.product.models.Product;
import com.liverpool.mx.product.models.ProductResponseDto;
import com.liverpool.mx.product.repositories.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository repository;
    private InventoryFeignClient inventoryFeignClient;
    private ProductMapper productMapper;

    public ProductServiceImpl(IProductRepository repository, InventoryFeignClient inventoryFeignClient,
            ProductMapper productMapper) {
        this.repository = repository;
        this.inventoryFeignClient = inventoryFeignClient;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductResponseDto> searchProducts(String query) {
        if (query.isEmpty()) {
            throw new BadRequestException("Keyword cannot be null or empty");
        }

        List<Product> products = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);

        return products.stream().map(product -> {
            InventoryDto inventory = null;
            try {
                inventory = inventoryFeignClient.findStock(product.getId());
            } catch(Exception e) {}
            return productMapper.toResponseDto(product, inventory);
        }).toList();
        
    }

}
