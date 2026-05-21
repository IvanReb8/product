package com.liverpool.mx.product.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.liverpool.mx.product.models.InventoryDto;

@FeignClient(name = "inventory", url = "http://localhost:8080/api/v1/inventory")
public interface InventoryFeignClient {

    @GetMapping("/{id}")
    InventoryDto findStock(@PathVariable String id);

}
