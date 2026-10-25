package com.dsai.travelflux.repository;

import com.dsai.travelflux.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueloRepository extends JpaRepository<Vuelo, Long> {
}