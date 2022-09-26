package com.alten.hotel_api.repository;

import com.alten.hotel_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
