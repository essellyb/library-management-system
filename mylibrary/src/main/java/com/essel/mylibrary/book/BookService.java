package com.essel.mylibrary.book;

import com.essel.mylibrary.user.User;
import com.essel.mylibrary.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isReserved(book.getIsReserved())
                .reservedBy(book.getReservedBy() != null ? book.getReservedBy().getUsername() : null)
                .build();
    }

    public BookResponse createBook(BookRequest request) {
        Book newBook = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isReserved(false)
                .build();
        return mapToResponse(bookRepository.save(newBook));
    }

    public BookResponse viewBookById(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        return mapToResponse(book);
    }

    public List<BookResponse> viewAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public BookResponse updateBook(BookRequest updateBook, Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        return mapToResponse(bookRepository.save(book));
    }

    public void deleteBookById(Integer id) {
        bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
        bookRepository.deleteById(id);
    }

    public BookResponse reserveBook(Integer id, String email) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));

        if (book.getIsReserved()) {
            throw new RuntimeException("Book is already reserved");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setIsReserved(true);
        book.setReservedBy(user);

        return mapToResponse(bookRepository.save(book));
    }

    public BookResponse unreserveBook(Integer id, String email) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));

        if (!book.getIsReserved()) {
            throw new RuntimeException("Book is already unreserved");
        }

        if (!book.getReservedBy().getUsername().equals(email)) {
            throw new RuntimeException("Unauthorized. You cannot unreserve a book you did not reserve");
        }

        book.setIsReserved(false);
        book.setReservedBy(null);

        return mapToResponse(bookRepository.save(book));
    }
}