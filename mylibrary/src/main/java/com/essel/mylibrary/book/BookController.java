package com.essel.mylibrary.book;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest newBook) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(newBook));
    }

    @GetMapping
    public ResponseEntity<List<Book>> viewAllBooks() {
        return ResponseEntity.ok(bookService.viewAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> viewBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.viewBookById(id));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> update(@PathVariable Integer id, @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/reserve/{id}")
    public ResponseEntity<Book> reserveBook(@PathVariable Integer id, @RequestBody String username) {
        return ResponseEntity.ok(bookService.reserveBook(id, username));
    }

    @PostMapping("/unreserve/{id}")
    public ResponseEntity<Book> unreserveBook(@PathVariable Integer id, @RequestBody String username) {
        return ResponseEntity.ok(bookService.unreserveBook(id, username));
    }
}
