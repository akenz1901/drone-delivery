package com.drone.data.model.dto;

import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Status;
import lombok.Data;

import java.util.List;

@Data
public class DroneDto {
    private Integer weight;
    private Status state;
    private LoadingContainer container;
    private Integer batter_percentage;
}
