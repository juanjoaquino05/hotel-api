package com.alten.hotel_api.service;

import com.alten.hotel_api.exception.ResourceNotFoundException;
import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.repository.ReservationRepository;
import com.alten.hotel_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public UserService(UserRepository userRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
    }


    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new ResourceNotFoundException("User");

        return user.get();
    }

    public void cancelReservation(Long userId, Long reservationId) {
        User user = getUser(userId);

        Optional<Reservation> reservation = reservationRepository.findById(reservationId);

        if(!reservation.isPresent()) return;

        Reservation toUpdate = reservation.get();
        toUpdate.setIsCancelled(true);

        reservationRepository.save(toUpdate);

    }
}
