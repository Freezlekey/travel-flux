package com.dsai.travelflux.controller;

import com.dsai.travelflux.exception.HotelNotFoundException;
import com.dsai.travelflux.model.Hotel;
import com.dsai.travelflux.repository.HotelRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.dsai.travelflux.model.Hotel;

import java.util.List;

@RestController
@RequestMapping("/api/hoteles")
public class HotelController {

    private final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    // READ: obtener los tres hoteles destacados como novedades
    @GetMapping("/novedades")
    public List<Hotel> obtenerNovedades() {
        return hotelRepository.findAll()
                .stream()
                .limit(3)
                .toList();
    }

    // READ: obtener todos los hoteles
    @GetMapping
    public List<Hotel> obtenerHoteles() {
        return hotelRepository.findAll();
    }

    // READ: obtener un hotel por ID
    @Operation(
            summary = "Obtener un hotel por ID",
            description = "Devuelve los detalles de un hotel a partir de su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel encontrado"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerHotel(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREATE: crear un hotel
    @Operation(summary = "Crear un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel creado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        hotel.setId(null);
        return hotelRepository.save(hotel);
    }

    // UPDATE: actualizar un hotel existente
    @Operation(summary = "Actualizar un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel actualizado"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    @PutMapping("/{id}")
    public Hotel actualizarHotel(
            @PathVariable Long id,
            @RequestBody Hotel hotelActualizado) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        hotel.setNombre(hotelActualizado.getNombre());
        hotel.setUbicacion(hotelActualizado.getUbicacion());
        hotel.setEstrellas(hotelActualizado.getEstrellas());
        hotel.setDescripcion(hotelActualizado.getDescripcion());
        hotel.setHistoria(hotelActualizado.getHistoria());
        hotel.setImagenUrl(hotelActualizado.getImagenUrl());
        hotel.setPopularidad(hotelActualizado.getPopularidad());
        hotel.setUrlOficial(hotelActualizado.getUrlOficial());

        return hotelRepository.save(hotel);
    }

    // DELETE: eliminar un hotel
    @Operation(summary = "Eliminar un hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hotel eliminado"),
            @ApiResponse(responseCode = "404", description = "Hotel no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarHotel(@PathVariable Long id) {

        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException(id));

        hotelRepository.delete(hotel);
    }
}