package com.dsai.travelflux.controller;

import com.dsai.travelflux.model.Destacado;
import com.dsai.travelflux.model.Hotel;
import com.dsai.travelflux.model.Vuelo;
import com.dsai.travelflux.model.Oferta;
import com.dsai.travelflux.repository.HotelRepository;
import com.dsai.travelflux.repository.VueloRepository;
import com.dsai.travelflux.repository.OfertaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/destacados")
public class DestacadoController {

    private final HotelRepository hotelRepository;
    private final VueloRepository vueloRepository;
    private final OfertaRepository ofertaRepository;

    public DestacadoController(
            HotelRepository hotelRepository,
            VueloRepository vueloRepository,
            OfertaRepository ofertaRepository) {
        this.hotelRepository = hotelRepository;
        this.vueloRepository = vueloRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @GetMapping
    public List<Destacado> obtenerDestacados() {

        List<Destacado> destacados = new ArrayList<>();

        List<Hotel> hoteles = hotelRepository.findAll().stream()
                .filter(hotel -> hotel.getEstrellas() >= 4)
                .sorted((hotel1, hotel2) ->
                        Integer.compare(hotel2.getEstrellas(), hotel1.getEstrellas()))
                .limit(3)
                .toList();

        for (Hotel hotel : hoteles) {
            destacados.add(new Destacado(
                    hotel.getId(),
                    "hotel",
                    hotel.getNombre(),
                    hotel.getUbicacion(),
                    hotel.getImagenUrl(),
                    "/detail.html?id=" + hotel.getId() + "&tipo=hotel"
            ));
        }

        List<Vuelo> vuelos = vueloRepository.findAll().stream()
                .limit(2)
                .toList();

        for (Vuelo vuelo : vuelos) {
            destacados.add(new Destacado(
                    vuelo.getId(),
                    "vuelo",
                    vuelo.getOrigen() + " - " + vuelo.getDestino(),
                    vuelo.getOrigen() + " - " + vuelo.getDestino(),
                    vuelo.getImagenUrl(),
                    "/detail.html?id=" + vuelo.getId() + "&tipo=vuelo"
            ));
        }

        List<Oferta> ofertas = ofertaRepository.findAll();
        for (Oferta oferta : ofertas) {
            destacados.add(new Destacado(
                    oferta.getId(),
                    "oferta",
                    "Oferta de viaje a " + oferta.getDestino(),
                    oferta.getDestino(),
                    oferta.getImagenUrl(),
                    "/detail.html?id=" + oferta.getId() + "&tipo=oferta"
            ));
        }

        return destacados;
    }
}