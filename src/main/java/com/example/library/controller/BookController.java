package com.example.library.controller;

import com.example.library.dto.ResponseMessage;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/books")
    public class BookController {
        @Autowired
        private BookService bookService;

        @GetMapping
        public ResponseEntity<?> getAllBooks() {
            List<Book> books = bookService.getAllBooks();
            if (books.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponseMessage("No books found"));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseMessage("Books fetched successfully", books));
        }

        @GetMapping("/author/{author}")
        public ResponseEntity<?> getBooksByAuthor(@PathVariable String author) {
           try{
               List<Book> bookByAuthor = bookService.getBooksByAuthor(author);
               return ResponseEntity
                       .status(HttpStatus.OK)
                       .body(new ResponseMessage("Book added successfully", bookByAuthor));
           } catch (Exception e){
               return ResponseEntity
                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(new ResponseMessage("Error adding book: " + e.getMessage()));
           }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
            boolean isDeleted = bookService.deleteBook(id);
            if (isDeleted) {
                // Return success message if deletion is successful
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(new ResponseMessage("Book deleted successfully"));
            } else {
                // Return error message if the book is not found or deletion fails
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new ResponseMessage("Book not found"));
            }


        }

        @PostMapping
        public ResponseEntity<?> addBook(@RequestBody Book book) {
            try {
                // Add book logic
                Book savedBook = bookService.saveBook(book);
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(new ResponseMessage("Book added successfully", savedBook));
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseMessage("Error adding book: " + e.getMessage()));
            }
        }
    }

