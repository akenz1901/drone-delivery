package com.drone.service;


import com.drone.data.model.Drone;
import com.drone.data.model.Medication;
import com.drone.service.exceptions.LoadingMedicationException;
import com.drone.service.exceptions.NonExisitingDroneException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LoadingService {

    Drone loadDroneWithMedication(Long droneId, Medication medication) throws LoadingMedicationException, NonExisitingDroneException;
    List<Medication> loadedMedicationsItems(Long droneId);
    List<Drone> getAvailableDronesForLoading() throws NonExisitingDroneException;
    Integer checkGivenDroneBattery(Long id) throws NonExisitingDroneException;
}
