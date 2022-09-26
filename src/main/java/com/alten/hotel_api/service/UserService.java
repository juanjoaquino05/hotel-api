package com.alten.hotel_api.service;

import com.alten.hotel_api.converter.ReservationConverter;
import com.alten.hotel_api.exception.ResourceNotFoundException;
import com.alten.hotel_api.helper.ReservationValidator;
import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.repository.ReservationRepository;
import com.alten.hotel_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

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
