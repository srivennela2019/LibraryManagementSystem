package com.example.library.service;

import com.example.library.repository.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.library.entity.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add or Update a book (Save operation)
    public Book saveBook(Book book) {

        System.out.println(book);
        return bookRepository.save(book);
    }

    // Find a book by ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Get all books
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Get books by author
    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthor(authorName);
    }

    // Delete a book by ID
    public boolean deleteBook(Long id) {
        try {
            if (bookRepository.existsById(id)) {
                bookRepository.deleteById(id);
                return true;
            } else {
                return false;  // Return false if the book is not found
            }
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Data integrity violation while deleting book", e);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while deleting the book", e);
        }
    }


}
