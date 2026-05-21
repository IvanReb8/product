package com.liverpool.mx.product.services;

import java.util.List;

import com.liverpool.mx.product.models.ProductResponseDto;

public interface IProductService {

    List<ProductResponseDto> searchProducts(String query);

}
