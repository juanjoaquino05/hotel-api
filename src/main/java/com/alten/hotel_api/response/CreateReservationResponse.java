package com.alten.hotel_api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CreateReservationResponse {
    private String roomNumber;
    private String user;
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime date;
}
