package com.alten.hotel_api.controller;

import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.service.RoomService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class RoomController {
    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<List<LocalDate>> getAvailability(@PathVariable Long id){
        log.info("{} request received.", Thread.currentThread().getStackTrace()[1].getMethodName());
        Room room = service.getRoom(id);
        log.info("{} request completed.", Thread.currentThread().getStackTrace()[1].getMethodName());

        return ResponseEntity.ok().body(service.checkAvailability(room));
    }
}
