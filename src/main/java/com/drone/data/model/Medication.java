package com.drone.data.model;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ToString.Exclude
    private String imageUrl;

    private String code;
    private Integer weight;

    @ManyToOne(fetch = FetchType.LAZY)
    LoadingContainer loadingContainer;
}
