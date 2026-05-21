package com.liverpool.mx.product;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.liverpool.mx.product.models.Product;
import com.liverpool.mx.product.repositories.IProductRepository;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


@Configuration
public class LoadDataConfig {

    @Bean
    CommandLineRunner startDataBase(IProductRepository repository){
        return args -> {
            try(InputStream inputStream = TypeReference.class.getResourceAsStream("/products.json")){
                ObjectMapper objectMapper = new ObjectMapper();
                List<Product> products = objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {});
                repository.saveAll(products);
                System.out.println("Data successfully loaded from JSON");
            } catch(Exception e){
                System.out.println("Error loading data: "+ e.getMessage());
            }
        };
    }

}
