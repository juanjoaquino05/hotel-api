package com.alten.hotel_api.controller;

import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;
import com.alten.hotel_api.service.ReservationService;
import lombok.CustomLog;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reservations")
@Validated
@Slf4j
public class ReservationController {
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> makeReservation(@Valid @RequestBody CreateReservationRequest request) throws Exception {
        log.info("{} request received.", Thread.currentThread().getStackTrace()[1].getMethodName());
        CreateReservationResponse reservation = service.createRoomReservation(request);
        log.info("{} request completed.", Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok().body(reservation);
    }

    @PutMapping("/{rid}/user/{id}")
    public ResponseEntity<CreateReservationResponse> modifyReservation(@PathVariable Long id, @PathVariable Long rid, @RequestBody Reservation reservation) throws Exception {
        log.info("{} request received.", Thread.currentThread().getStackTrace()[1].getMethodName());
        ResponseEntity response = service.modifyReservation(id, rid, reservation);
        log.info("{} request completed.", Thread.currentThread().getStackTrace()[1].getMethodName());

        return response;
    }

    @DeleteMapping("/{rid}/user/{id}")
    public ResponseEntity cancelReservation(@PathVariable Long id, @PathVariable Long rid){
        log.info("{} request received.", Thread.currentThread().getStackTrace()[1].getMethodName());
        service.cancelReservation(id, rid);
        log.info("{} request completed.", Thread.currentThread().getStackTrace()[1].getMethodName());

        return ResponseEntity.ok().body(null);
    }
}
