package com.example.library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.library.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Find books by title (method query)
    List<Book> findByTitle(String title);

    // Custom JPQL query to find books published after a specific year
    @Query("SELECT b FROM Book b WHERE b.author LIKE :author")
    List<Book> findBooksByAuthor(@Param("author") String author);
  // Count books by a specific author
  //long countByAuthorName(String authorName);
}
