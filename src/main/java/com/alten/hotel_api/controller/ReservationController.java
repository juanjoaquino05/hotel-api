package com.alten.hotel_api.controller;

import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;
import com.alten.hotel_api.service.ReservationService;
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
public class ReservationController {
    Logger logger = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CreateReservationResponse> makeReservation(@Valid @RequestBody CreateReservationRequest request) throws Exception {
        logger.info(request.toString());

        CreateReservationResponse reservation = service.createRoomReservation(request);

        return ResponseEntity.ok().body(reservation);
    }

    @PutMapping("/{rid}/user/{id}")
    public ResponseEntity<CreateReservationResponse> modifyReservation(@PathVariable Long id, @PathVariable Long rid, @RequestBody Reservation reservation) throws Exception {

        return service.modifyReservation(id, rid, reservation);

    }

    @DeleteMapping("/{rid}/user/{id}")
    public ResponseEntity cancelReservation(@PathVariable Long id, @PathVariable Long rid){
        service.cancelReservation(id, rid);

        return ResponseEntity.ok().body(null);
    }
}
