package com.example.library.repository;


import com.example.library.entity.Loan;
import com.example.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.library.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    // Find loans by the associated user's ID
    List<Loan> findByUserId(Long userId);

    // Find loans by the associated book's ID
    List<Loan> findByBookId(Long bookId);

    // Find active loans
    List<Loan> findByReturnDateIsNull();

    // Custom query: Find loans by user's name (requires join with User)
    List<Loan> findByUserName(String userName);

    // Optional: Find loans by a combination of user ID and book ID
    List<Loan> findByUserIdAndBookId(Long userId, Long bookId);
}
