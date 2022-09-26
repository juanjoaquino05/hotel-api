package com.example.hotel_api.controller;

import com.example.hotel_api.model.Room;
import com.example.hotel_api.service.RoomService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@Validated
public class RoomController {
    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<List<LocalDate>> getAvailability(@PathVariable Long id){
        Room room = service.getRoom(id);

        return ResponseEntity.ok().body(service.checkAvailability(room));
    }
}
