package com.example.lab.service;


import com.example.lab.model.Author;
import com.example.lab.model.DTO.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {


    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDto authorDto);
    Optional<Author> update(Long id,AuthorDto authorDto);
    void delete(Long id);
}
