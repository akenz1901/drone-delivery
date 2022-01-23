package com.drone.data.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private Model model;

    private Integer weight;
    private Integer battery_percentage;

    @Enumerated(EnumType.STRING)
    private Status state;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private LoadingContainer medicationLoadList;


}
