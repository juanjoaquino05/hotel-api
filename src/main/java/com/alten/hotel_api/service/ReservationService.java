package com.alten.hotel_api.service;

import com.alten.hotel_api.constant.ErrorMessages;
import com.alten.hotel_api.converter.ReservationConverter;
import com.alten.hotel_api.exception.ReservedDateException;
import com.alten.hotel_api.exception.ResourceNotFoundException;
import com.alten.hotel_api.helper.ReservationValidator;
import com.alten.hotel_api.model.Room;
import com.alten.hotel_api.model.User;
import com.alten.hotel_api.repository.ReservationRepository;
import com.alten.hotel_api.model.Reservation;
import com.alten.hotel_api.repository.RoomRepository;
import com.alten.hotel_api.request.CreateReservationRequest;
import com.alten.hotel_api.response.CreateReservationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
//    private Logger log =
Logger logger = LoggerFactory.getLogger(ReservationService.class);
    private ReservationRepository reservationRepository;
    private ReservationValidator reservationValidator;
    private UserService userService;
    private RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, ReservationValidator reservationValidator, UserService userService, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationValidator = reservationValidator;
        this.userService = userService;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> getRoomReservationsBetween(Long id, LocalDate start, LocalDate end){
        return reservationRepository.findAllByRoomIdAndStartDateBetween(id, start.atStartOfDay(), end.atStartOfDay());
    }
    public CreateReservationResponse createRoomReservation(@Valid @RequestBody CreateReservationRequest request) throws Exception {
        reservationValidator.validateCreateRequest(request);

        User user = userService.getUser(request.getUser());
        Room room = roomRepository.findById(request.getRoom()).orElseThrow(() -> new ResourceNotFoundException("Room"));

        Reservation newReservation = ReservationConverter.convertCreateRequest(request, room, user);

        if(reservedBetweenDates(room, newReservation)) throw new ReservedDateException(ErrorMessages.ROOM_ALREADY_RESERVE);

        newReservation = reservationRepository.save(newReservation);

        return ReservationConverter.convertReservationToResponse(newReservation);
    }

    private Boolean reservedBetweenDates(Room room, Reservation reservation) {
        List<Reservation> reservations =
                reservationRepository.findAllByRoomIdAndIsCancelledAndStartDateBetweenOrEndDateBetween(
                        room.getId(), false, reservation.getStartDate(),
                        reservation.getEndDate(),
                        reservation.getStartDate(), reservation.getEndDate());

        return (reservations.size() > 0);
    }
}
