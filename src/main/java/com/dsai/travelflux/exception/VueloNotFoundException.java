package com.dsai.travelflux.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VueloNotFoundException extends RuntimeException {

    public VueloNotFoundException(Long id) {
        super("Vuelo no encontrado con id: " + id);
    }
}