package com.example.lab.service;

import com.example.lab.model.Book;
import com.example.lab.model.DTO.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();

    Page<Book> findAllByPagination(Pageable pageable);
    Optional<Book> findById(Long id);
    Optional<Book>  edit(Long id, BookDto bookDto);
    Optional<Book> save(BookDto bookDto);

    void delete(Long id);

    Optional<Book> markAsTaken(Long id);

}
