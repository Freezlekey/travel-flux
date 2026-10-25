package com.dsai.travelflux.config;

import com.dsai.travelflux.model.Hotel;
import com.dsai.travelflux.repository.HotelRepository;
import com.dsai.travelflux.model.Vuelo;
import com.dsai.travelflux.repository.VueloRepository;
import com.dsai.travelflux.model.Oferta;
import com.dsai.travelflux.repository.OfertaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            HotelRepository hotelRepository,
            VueloRepository vueloRepository,
            OfertaRepository ofertaRepository) {

        return args -> {

            if (hotelRepository.count() == 0) {
                hotelRepository.save(new Hotel(
                        "Hotel Mediterráneo",
                        "Guardamar del Segura, España",
                        4,
                        "Hotel cerca de la playa, ideal para disfrutar del Mediterráneo.",
                        "Un alojamiento de ambiente mediterráneo.",
                        "https://cf.bstatic.com/xdata/images/hotel/max1024x768/113213458.jpg?k=928244b7215871dc936d389ac06370089205c2f202596689d306fb72b5a4b044&o=",
                        "Popular por su ubicación cerca de la playa.",
                        ""
                ));

                hotelRepository.save(new Hotel(
                        "Hotel Costa Azul",
                        "Palma de Mallorca, España",
                        4,
                        "Hotel situado en el Paseo Marítimo de Palma.",
                        "Un hotel conocido por su ubicación en Mallorca.",
                        "https://cf.bstatic.com/xdata/images/hotel/max1024x768/349430367.jpg?k=79b8fc30fda81e17df2953a1cfca82c04754020027d24cff2eeb66f3eccc490e&o=",
                        "Popular por sus instalaciones y su ubicación.",
                        "https://www.hotelcostaazul.es/"
                ));

                hotelRepository.save(new Hotel(
                        "The Plaza Hotel",
                        "Nueva York, Estados Unidos",
                        5,
                        "Hotel emblemático situado en Nueva York.",
                        "Un hotel histórico de lujo.",
                        "https://cdn.kiwicollection.com/media/property/PR006187/xl/006187-01-The%20Plaza%20aerial-at%20The%20Plaza%20Hotel.jpg?cb=1762195840",
                        "Popular por su historia y su categoría.",
                        "https://www.theplazany.com/"
                ));

                hotelRepository.save(new Hotel(
                        "Hotel Imperial",
                        "Viena, Austria",
                        5,
                        "Hotel histórico de lujo en Viena.",
                        "Un establecimiento tradicional de la capital austríaca.",
                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/21/71/21/11/the-hotel-imperial-is.jpg?w=900&h=500&s=1",
                        "Popular por su arquitectura y su historia.",
                        "https://www.marriott.com/"
                ));

                hotelRepository.save(new Hotel(
                        "Hotel Mar",
                        "Ibiza, España",
                        3,
                        "Alojamiento turístico en Ibiza.",
                        "Hotel orientado a disfrutar de las vacaciones en la isla.",
                        "https://cf.bstatic.com/xdata/images/hotel/max1024x768/711925671.jpg?k=4d9a54c9c83b6d57070ebedd597cbc21bef776f9518f8406bd684d92eb0b6874&o=",
                        "Popular por sus piscinas y servicios.",
                        "https://www.azulinehotels.com/"
                ));

                hotelRepository.save(new Hotel(
                        "Grand Hotel Central",
                        "Barcelona, España",
                        5,
                        "Hotel situado en el centro de Barcelona.",
                        "Hotel urbano con vistas y servicios de lujo.",
                        "https://www.thehotelguru.com/_images/22/c5/22c5b1af40dedb5dfa0d802a75aafa81/s1654x900.jpg",
                        "Popular por su ubicación y su piscina.",
                        "https://www.grandhotelcentral.com/"
                ));

                hotelRepository.save(new Hotel(
                        "Eurostars Hotel Real",
                        "Santander, España",
                        5,
                        "Hotel histórico con vistas a la bahía de Santander.",
                        "Un establecimiento emblemático de la ciudad.",
                        "https://cf.bstatic.com/xdata/images/hotel/max1024x768/151815986.jpg?k=adf8299e66f223ab1778d986fcbfa96f45109e37686e3c0464159d55c987b524&o=",
                        "Popular por su arquitectura y sus vistas.",
                        "https://www.eurostarshotels.com/"
                ));

                hotelRepository.save(new Hotel(
                        "The Palace, a Luxury Collection Hotel",
                        "Madrid, España",
                        5,
                        "Hotel histórico situado en Madrid.",
                        "Un establecimiento clásico de lujo.",
                        "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/34/5a/4d/21/facade-of-the-palace.jpg?w=900&h=500&s=1",
                        "Popular por su historia y ubicación.",
                        "https://www.marriott.com/"
                ));
            }

            if (vueloRepository.count() == 0) {
                vueloRepository.save(new Vuelo(
                        "Madrid", "Alicante",
                        "España", "España",
                        "1h 10min", "Directo",
                        "Iberia, Air Europa",
                        "MAD", "ALC", "images/MA.png"
                ));

                vueloRepository.save(new Vuelo(
                        "Barcelona", "Roma",
                        "España", "Italia",
                        "1h 55min", "Directo",
                        "ITA Airways, Ryanair, Vueling, Wizz Air Malta",
                        "BCN", "FCO", "images/BR.png"
                ));

                vueloRepository.save(new Vuelo(
                        "Madrid", "París",
                        "España", "Francia",
                        "2h 05min", "Directo",
                        "Air France, Iberia",
                        "MAD", "CDG", "images/MP.png"
                ));

                vueloRepository.save(new Vuelo(
                        "Valencia", "Londres",
                        "España", "Reino Unido",
                        "2h 35min", "Directo",
                        "Vueling, easyJet, Wizz Air UK",
                        "VLC", "LGW", "images/VL.png"
                ));
            }

            if (ofertaRepository.count() == 0) {

                Hotel hotelAlicante = hotelRepository.findAll().stream()
                        .filter(h -> h.getNombre().equals("Hotel Mediterráneo"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                "No se encontró el Hotel Mediterráneo"
                        ));

                Vuelo vueloAlicante = vueloRepository.findAll().stream()
                        .filter(v -> v.getOrigen().equals("Madrid")
                                && v.getDestino().equals("Alicante"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                "No se encontró el vuelo Madrid - Alicante"
                        ));
                ofertaRepository.save(new Oferta(
                        "Alicante",
                        20,
                        "2026-06-01",
                        "2026-06-30",
                        "Oferta de viaje a Alicante con un 20% de descuento.",
                        hotelAlicante,
                        vueloAlicante,
                        "images/alicante.jpg"
                ));
                Hotel hotelRoma = hotelRepository.findAll().stream()
                        .filter(h -> h.getNombre().equals("The Plaza Hotel"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                "No se encontró The Plaza Hotel"
                        ));

                Vuelo vueloRoma = vueloRepository.findAll().stream()
                        .filter(v -> v.getOrigen().equals("Barcelona")
                                && v.getDestino().equals("Roma"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException(
                                "No se encontró el vuelo Barcelona - Roma"
                        ));
                ofertaRepository.save(new Oferta(
                        "Roma",
                        15,
                        "2026-07-10",
                        "2026-07-25",
                        "Oferta de viaje a Roma con un 15% de descuento.",
                        hotelRoma,
                        vueloRoma,
                        "images/roma.jpg"
                ));
            }
        };
    }
}

