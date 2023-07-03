package com.example.lab.model.DTO;

import com.example.lab.model.Country;
import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class AuthorDto {
    private String name;
    private String surname;

    private Long country;

    public AuthorDto(String name, String surname, Long country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
