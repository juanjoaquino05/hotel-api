package com.example.hotel_api.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity(name = "Hotel")
@Data
@NoArgsConstructor
@Table(
    name = "hotel",
    uniqueConstraints = {
        @UniqueConstraint(name = "hotel_name_unique", columnNames = "name")
    }
)
public class Hotel {

    @Id
    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "student_sequence"
    )
    @Column(
        name = "id"
    )
    private Long id;

    @Column(
        name = "name",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String name;

    @OneToMany(
        mappedBy = "hotel",
        orphanRemoval = true,
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<Room> rooms = new ArrayList<>();

}
