package com.dsai.travelflux.repository;

import com.dsai.travelflux.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}