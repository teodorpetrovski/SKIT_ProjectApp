package com.example.lab.service.Implementation;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.DTO.BookDto;
import com.example.lab.model.exceptions.AuthorNotFoundException;
import com.example.lab.model.exceptions.BookNotFoundException;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.BookRepository;
import com.example.lab.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Page<Book> findAllByPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.findById(id).orElseThrow(BookNotFoundException::new);
        book.setName(bookDto.getName());

        Author author=this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(AuthorNotFoundException::new);
        book.setAuthor(author);
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

       return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(AuthorNotFoundException::new);
        Book book=new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        Book book=this.findById(id).orElseThrow(BookNotFoundException::new);
        this.bookRepository.delete(book);
    }

    @Override
    @Transactional
    public Optional<Book> markAsTaken(Long id) {
        Book book=this.findById(id).orElseThrow(BookNotFoundException::new);
        book.setAvailableCopies(book.getAvailableCopies()-1);
       return Optional.of(this.bookRepository.save(book));
    }
}
