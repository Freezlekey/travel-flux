package com.dsai.travelflux.controller;

import com.dsai.travelflux.model.Vuelo;
import com.dsai.travelflux.repository.VueloRepository;
import com.dsai.travelflux.exception.VueloNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

    private final VueloRepository vueloRepository;

    public VueloController(VueloRepository vueloRepository) {
        this.vueloRepository = vueloRepository;
    }

    // READ
    @GetMapping("/novedades")
    public List<Vuelo> obtenerNovedades() {
        return vueloRepository.findAll()
                .stream()
                .limit(3)
                .toList();
    }

    // READ
    @GetMapping
    public List<Vuelo> obtenerVuelos() {
        return vueloRepository.findAll();
    }

    // READ
    @Operation(
            summary = "Obtener un vuelo por ID",
            description = "Devuelve los detalles de un vuelo a partir de su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo encontrado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @GetMapping("/{id}")
    public Vuelo obtenerVueloPorId(@PathVariable Long id) {
        return vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException(id));
    }

    // CREATE
    @Operation(summary = "Crear un vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vuelo creado")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vuelo crearVuelo(@RequestBody Vuelo vuelo) {
        vuelo.setId(null);
        return vueloRepository.save(vuelo);
    }

    // UPDATE
    @Operation(summary = "Actualizar un vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vuelo actualizado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @PutMapping("/{id}")
    public Vuelo actualizarVuelo(
            @PathVariable Long id,
            @RequestBody Vuelo vueloActualizado) {

        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException(id));

        vuelo.setOrigen(vueloActualizado.getOrigen());
        vuelo.setDestino(vueloActualizado.getDestino());
        vuelo.setPaisOrigen(vueloActualizado.getPaisOrigen());
        vuelo.setPaisDestino(vueloActualizado.getPaisDestino());
        vuelo.setDuracion(vueloActualizado.getDuracion());
        vuelo.setTipo(vueloActualizado.getTipo());
        vuelo.setAerolineas(vueloActualizado.getAerolineas());
        vuelo.setCodigoOrigen(vueloActualizado.getCodigoOrigen());
        vuelo.setCodigoDestino(vueloActualizado.getCodigoDestino());
        vuelo.setImagenUrl(vueloActualizado.getImagenUrl());

        return vueloRepository.save(vuelo);
    }

    // DELETE
    @Operation(summary = "Eliminar un vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vuelo eliminado"),
            @ApiResponse(responseCode = "404", description = "Vuelo no encontrado")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarVuelo(@PathVariable Long id) {

        Vuelo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new VueloNotFoundException(id));

        vueloRepository.delete(vuelo);
    }
}