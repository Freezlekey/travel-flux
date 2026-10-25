package com.dsai.travelflux.controller;

import com.dsai.travelflux.dto.OfertaRequest;
import com.dsai.travelflux.exception.HotelNotFoundException;
import com.dsai.travelflux.exception.VueloNotFoundException;
import com.dsai.travelflux.model.Oferta;
import com.dsai.travelflux.repository.OfertaRepository;
import com.dsai.travelflux.repository.HotelRepository;
import com.dsai.travelflux.repository.VueloRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

    private final OfertaRepository ofertaRepository;
    private final HotelRepository hotelRepository;
    private final VueloRepository vueloRepository;

    public OfertaController(
            OfertaRepository ofertaRepository,
            HotelRepository hotelRepository,
            VueloRepository vueloRepository) {

        this.ofertaRepository = ofertaRepository;
        this.hotelRepository = hotelRepository;
        this.vueloRepository = vueloRepository;
    }

    // Obtener todas las ofertas
    @GetMapping
    public List<Oferta> obtenerOfertas() {
        return ofertaRepository.findAll();
    }

    // Obtener una oferta por ID
    @GetMapping("/{id}")
    public Oferta obtenerOfertaPorId(@PathVariable Long id) {
        return ofertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Oferta no encontrada con ID: " + id
                ));
    }

    // Crear una oferta
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Oferta crearOferta(@RequestBody OfertaRequest request) {

        Oferta oferta = new Oferta();

        oferta.setDestino(request.getDestino());
        oferta.setDescuento(request.getDescuento());
        oferta.setFechaInicio(request.getFechaInicio());
        oferta.setFechaFin(request.getFechaFin());
        oferta.setDescripcion(request.getDescripcion());
        oferta.setImagenUrl(request.getImagenUrl());

        oferta.setHotel(
                hotelRepository.findById(request.getHotelId())
                        .orElseThrow(() ->
                                new HotelNotFoundException(request.getHotelId()))
        );

        oferta.setVuelo(
                vueloRepository.findById(request.getVueloId())
                        .orElseThrow(() ->
                                new VueloNotFoundException(request.getVueloId()))
        );

        return ofertaRepository.save(oferta);
    }

    // Actualizar una oferta
    @PutMapping("/{id}")
    public Oferta actualizarOferta(
            @PathVariable Long id,
            @RequestBody OfertaRequest request) {

        Oferta oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Oferta no encontrada con ID: " + id
                ));

        oferta.setDestino(request.getDestino());
        oferta.setDescuento(request.getDescuento());
        oferta.setFechaInicio(request.getFechaInicio());
        oferta.setFechaFin(request.getFechaFin());
        oferta.setDescripcion(request.getDescripcion());
        oferta.setImagenUrl(request.getImagenUrl());

        oferta.setHotel(
                hotelRepository.findById(request.getHotelId())
                        .orElseThrow(() ->
                                new HotelNotFoundException(request.getHotelId()))
        );

        oferta.setVuelo(
                vueloRepository.findById(request.getVueloId())
                        .orElseThrow(() ->
                                new VueloNotFoundException(request.getVueloId()))
        );

        return ofertaRepository.save(oferta);
    }

    // Eliminar una oferta
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarOferta(@PathVariable Long id) {

        Oferta oferta = ofertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Oferta no encontrada con ID: " + id
                ));

        ofertaRepository.delete(oferta);
    }
}