package com.alten.hotel_api.service;

import com.alten.hotel_api.exception.ResourceNotFoundException;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new ResourceNotFoundException("User");

        return user.get();
    }
}
