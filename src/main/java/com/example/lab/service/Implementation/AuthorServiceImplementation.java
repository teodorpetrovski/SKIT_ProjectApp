package com.example.lab.service.Implementation;

import com.example.lab.model.Author;
import com.example.lab.model.Country;
import com.example.lab.model.DTO.AuthorDto;
import com.example.lab.model.exceptions.AuthorNotFoundException;
import com.example.lab.model.exceptions.CountryNotFoundException;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country=this.countryRepository.findById(authorDto.getCountry()).orElseThrow(CountryNotFoundException::new);
        Author author=new Author(authorDto.getName(),authorDto.getSurname(),country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> update(Long id, AuthorDto authorDto) {
        Author author=this.findById(id).orElseThrow(AuthorNotFoundException::new);
        Country country=this.countryRepository.findById(authorDto.getCountry()).orElseThrow(CountryNotFoundException::new);

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void delete(Long id) {
        Author author=this.findById(id).orElseThrow(AuthorNotFoundException::new);
        this.authorRepository.delete(author);

    }
}
