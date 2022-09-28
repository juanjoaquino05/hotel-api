package com.alten.hotel_api.repository;

import com.alten.hotel_api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
