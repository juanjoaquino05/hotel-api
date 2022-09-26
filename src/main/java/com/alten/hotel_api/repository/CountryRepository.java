package com.alten.hotel_api.repository;

import com.alten.hotel_api.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
