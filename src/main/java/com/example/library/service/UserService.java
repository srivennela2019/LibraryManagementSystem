package com.example.library.service;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.library.util.PasswordUtil;

import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Add or Update a Loan (Save operation)
    public User saveUser(User user) {
        System.out.println(user);
        return userRepository.save(user);
    }

    public boolean isUserDetailsValid(String email, String password){
        return email != null && !email.isEmpty()
                && password != null && !password.isEmpty();
    }



    public boolean authenticate(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        // Check if user exists
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Compare the provided password with the stored hashed password
            return PasswordUtil.matches(rawPassword, user.getPassword());
        }

        return false;  // User not found
    }

    public boolean isUserValid(User user) {
        System.out.println(user.getUsername() );
        System.out.println( user.getEmail() );
        System.out.println( user.getPassword() );
        System.out.println(user.getUsername() != null && !user.getUsername().isEmpty() && isUserDetailsValid(user.getEmail(), user.getPassword()));
        return user.getUsername() != null && !user.getUsername().isEmpty() && isUserDetailsValid(user.getEmail(), user.getPassword());

    }

    public boolean findEmailPassWord(String email, String password){
        return userRepository.existsByEmailAndPassword(email,password);
    }

    public User findByUsername(String username) {
        System.out.println();
        return userRepository.findByUsername(username);
    }

    public Boolean emailExist(String email) {
        System.out.println();
        return userRepository.existsByEmail(email);
    }
}
