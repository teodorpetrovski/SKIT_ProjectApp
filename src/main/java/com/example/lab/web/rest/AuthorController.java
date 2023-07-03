package com.example.lab.web.rest;

import com.example.lab.model.Author;
import com.example.lab.model.Country;
import com.example.lab.model.DTO.AuthorDto;
import com.example.lab.model.DTO.CountryDto;
import com.example.lab.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","*"})
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAll()
    {
        return this.authorService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDto authorDto)
    {
        return this.authorService.save(authorDto).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDto authorDto)
    {
        return this.authorService.update(id,authorDto).map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        this.authorService.delete(id);
        if(this.authorService.findById(id).isPresent())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
