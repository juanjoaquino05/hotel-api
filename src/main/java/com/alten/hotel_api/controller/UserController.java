package com.alten.hotel_api.controller;

import com.alten.hotel_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @DeleteMapping("/{id}/reservations/{rid}")
    public ResponseEntity<List<LocalDate>> cancelReservation(@PathVariable Long id, @PathVariable Long rid){
        service.cancelReservation(id, rid);

        return ResponseEntity.ok().body(null);
    }
}
