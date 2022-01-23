package com.drone.data.model.dto;

import com.drone.data.model.Medication;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoadingContainerDto {
    private List<Medication> medications = new ArrayList<>();
}
