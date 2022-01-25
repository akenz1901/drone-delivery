package com.drone.data.model;

import lombok.Data;
import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


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
    @Column(nullable = false)
    private Status state;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private LoadingContainer medicationLoadList;


}
