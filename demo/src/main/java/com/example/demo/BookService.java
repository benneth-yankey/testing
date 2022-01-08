package com.example.demo;

import antlr.ASTNULLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> getBooks() {
       return bookRepository.findAll();
   }

    public BookEntity saveBook(BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }
}
