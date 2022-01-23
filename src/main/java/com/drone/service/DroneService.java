package com.drone.service;

import com.drone.data.model.Drone;
import com.drone.data.model.dto.DroneDto;
import com.drone.service.exceptions.NonExisitingDroneException;
import com.drone.service.exceptions.WeightTooLargeException;


import java.util.List;

public interface DroneService {
    Drone registerDrone(Drone newDrone) throws WeightTooLargeException;
    List<Drone> findAllDrone();
    Drone findDrone(Long id);
    Drone updateDroneStatus(Long id, DroneDto dto) throws NonExisitingDroneException;
}
