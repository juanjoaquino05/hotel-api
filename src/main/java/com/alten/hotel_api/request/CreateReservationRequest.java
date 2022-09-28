package com.alten.hotel_api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.alten.hotel_api.constant.Reservations.DATE_FORMAT_PATTERN;

@Data
@NoArgsConstructor
//@ToString
public class CreateReservationRequest {
    @NotNull(message = "room may not be null")
    private Long room;

    @JsonFormat(pattern = DATE_FORMAT_PATTERN)
    @NotNull(message = "startDate may not be null")
    private String startDate;

    @JsonFormat(pattern = DATE_FORMAT_PATTERN)
    @NotNull(message = "endDate may not be null")
    private String endDate;

    @NotNull(message = "user may not be null")
    private Long user;
}
