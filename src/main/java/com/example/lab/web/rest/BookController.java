package com.example.lab.web.rest;

import com.example.lab.model.Book;
import com.example.lab.model.DTO.BookDto;
import com.example.lab.model.enums.Category;
import com.example.lab.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","*"})
@RequestMapping({"/api","/api/books"})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll()
    {
        return this.bookService.findAll();
    }


    @GetMapping("/pagination")
    public List<Book> getAllWithPagination(Pageable pageable)
    {
        return this.bookService.findAllByPagination(pageable).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id)
    {
        return this.bookService.findById(id).map((book -> ResponseEntity.ok().body(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto)
    {
        return this.bookService.save(bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id,@RequestBody BookDto bookDto)
    {
        return  this.bookService.edit(id,bookDto).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
         this.bookService.delete(id);
         if(this.bookService.findById(id).isPresent())
             return ResponseEntity.badRequest().build();
         return ResponseEntity.ok().build();
    }

    @PostMapping("/mark/{id}")
    public ResponseEntity markAsTaken(@PathVariable Long id)
    {
       return this.bookService.markAsTaken(id).map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("/categories")
    public List<String> getCategories()
        {
            return Stream.of(Category.values())
                    .map(Category::name)
                    .collect(Collectors.toList());
        }

}
