package com.alten.hotel_api.model;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "reservation",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "UniqueReservationPerRoomAndStartDate",
            columnNames = { "start_date", "room_id" }
        )
    }
)

public class Reservation {

    @Id
    @SequenceGenerator(
        name = "reservation_sequence",
        sequenceName = "reservation_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "reservation_sequence"
    )
    @Column(
            name = "id"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "room_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
            name = "reservation_room_fk"
        )
    )
    private Room room;

    @ManyToOne
    @JoinColumn(
        name = "user_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
                name = "reservation_user_fk"
        )
    )
    private User user;

    @Column(
        name = "start_date",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime startDate;

    @Column(
        name = "end_date",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime endDate;

    @Column(
            name = "created_date",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdDate;
}
