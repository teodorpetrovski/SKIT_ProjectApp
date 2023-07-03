package com.example.lab.service.Implementation;

import com.example.lab.model.Country;
import com.example.lab.model.DTO.CountryDto;
import com.example.lab.model.exceptions.AuthorNotFoundException;
import com.example.lab.model.exceptions.CountryNotFoundException;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImplementation implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImplementation(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDto countryDto) {
        Country country=new Country(countryDto.getName(),countryDto.getContinent());
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, CountryDto countryDto) {
       Country country=this.countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
       country.setName(countryDto.getName());
       country.setContinent(countryDto.getContinent());
       return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public void delete(Long id) {
        Country country=this.countryRepository.findById(id).orElseThrow(CountryNotFoundException::new);
        this.countryRepository.delete(country);
    }
}
