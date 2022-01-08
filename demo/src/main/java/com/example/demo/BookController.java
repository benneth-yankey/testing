package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> getBooks() {
       return bookService.getBooks();
    }

    @PostMapping
    public ResponseEntity<BookEntity> saveBook(@RequestBody BookEntity bookEntity) {
       BookEntity book = bookService.saveBook(bookEntity);
       return ResponseEntity.ok(book);
    }
}
