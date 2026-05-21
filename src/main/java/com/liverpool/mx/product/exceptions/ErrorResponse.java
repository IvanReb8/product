package com.liverpool.mx.product.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
    String path, 
    String message, 
    int status, 
    LocalDateTime date
) {}
