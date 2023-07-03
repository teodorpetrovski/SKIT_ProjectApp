package com.example.lab.service;

import com.example.lab.model.Country;
import com.example.lab.model.DTO.CountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);

    Optional<Country> save(CountryDto countryDto);

    Optional<Country> update(Long id,CountryDto countryDto);
    void delete(Long id);
}
