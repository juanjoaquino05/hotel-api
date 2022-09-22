package com.example.hotel_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@Entity(name = "Room")
@Table(name = "room")
public class Room {

    @Id
    @SequenceGenerator(
        name = "room_sequence",
        sequenceName = "room_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "room_sequence"
    )
    @Column(
        name = "id",
        updatable = false
    )
    private Long id;

    @Column(
        name = "created_at",
        nullable = false,
        columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime createdAt;

    @Column(
        name = "room_number",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String number;

    @ManyToOne
    @JoinColumn(
        name = "hotel_id",
        nullable = false,
        referencedColumnName = "id",
        foreignKey = @ForeignKey(
            name = "hotel_room_fk"
        )
    )
    private Hotel hotel;

    @OneToMany(
            mappedBy = "user",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Reservation> reservations = new ArrayList<>();

    public Room(String roomNumber,
                LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.number = roomNumber;
    }
}