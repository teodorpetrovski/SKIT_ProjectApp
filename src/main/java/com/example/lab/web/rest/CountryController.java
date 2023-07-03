package com.example.lab.web.rest;

import com.example.lab.model.Country;
import com.example.lab.model.DTO.CountryDto;
import com.example.lab.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAll()
    {
        return this.countryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDto countryDto)
    {
        return this.countryService.save(countryDto).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id,@RequestBody CountryDto countryDto)
    {
        return this.countryService.update(id,countryDto).map(country -> ResponseEntity.ok().body(country))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        this.countryService.delete(id);
        if(this.countryService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }


}
