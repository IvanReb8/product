package com.liverpool.mx.product.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.liverpool.mx.product.models.InventoryDto;
import com.liverpool.mx.product.models.Product;
import com.liverpool.mx.product.models.ProductResponseDto;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "inventory.stock", target = "stock")
    @Mapping(target = "inventoryStatus", expression = "java(calculateStatus(inventory))")
    ProductResponseDto toResponseDto(Product product, InventoryDto inventory);

    default String calculateStatus(InventoryDto inventory){
        if (inventory == null || inventory.getStock() <= 0) {
            return "UNAVAILABLE";
        }
        return "AVAILABLE";
    }

}
