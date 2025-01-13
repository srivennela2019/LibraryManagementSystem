package com.example.library.service;

import com.example.library.entity.Loan;
import com.example.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }
    // Add or Update a Loan (Save operation)
    public Loan saveLoan(Loan loan) {

        System.out.println(loan);
        return loanRepository.save(loan);
    }

    public List<Loan> findLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public List<Loan> findLoansByBookId(Long bookId) {
        return loanRepository.findByBookId(bookId);
    }

    public List<Loan> findByUserIdAndBookId(Long userId, Long bookId) {
        return loanRepository.findByUserIdAndBookId(userId, bookId);
    }

}
