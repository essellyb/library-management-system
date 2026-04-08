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

    public Book createBook(BookRequest request) {
        Book newBook = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .build();
        return bookRepository.save(newBook);
    }

    public Book viewBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Book with id "+ id + " not found"));
    }

    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(Book updatebook, Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id "+ id + "  not found"));

        book.setTitle(updatebook.getTitle());
        book.setAuthor(updatebook.getAuthor());

        return bookRepository.save(book);
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    public Book reserveBook(Integer id, String username) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.getIsReserved()) {
            throw new RuntimeException("Book already reserved");
        }


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setIsReserved(true);


        return bookRepository.save(book);
    }

    public Book unreserveBook(Integer id, String username) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with id "+ id + " not found"));

        if(book.getIsReserved()) {
            throw new RuntimeException("Book is already unreserved");
        }

        if(!book.getReservedBy().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized. You cannot unreserve a book you did not reserve");
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Book with id "+ id + " not found"));

        book.setIsReserved(false);


        return bookRepository.save(book);

    }
}
