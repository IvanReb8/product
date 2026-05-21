package com.liverpool.mx.product.exceptions;

public class NotFoundResourceException extends RuntimeException {
    public NotFoundResourceException(String id){
        super("Product with id: "+ id +" cannot be founded");
    }

}
