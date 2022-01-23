package com.drone.service;

import com.drone.data.model.Drone;
import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Medication;
import com.drone.data.model.dto.DroneDto;
import com.drone.data.repository.DroneRepository;
import com.drone.service.exceptions.NonExisitingDroneException;
import com.drone.service.exceptions.WeightTooLargeException;
import com.drone.service.mapping.DroneStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DroneServiceImpl implements DroneService{


    @Autowired
    DroneRepository droneRepository;

    @Autowired
    DroneStateMapper droneStateMapper;

    @Autowired
    LoadingContainerService loadingContainerService;


    @Override
    public Drone registerDrone(Drone newDrone) throws WeightTooLargeException {
        boolean droneWeightIsNotNegativeOrMoreThanLimit = newDrone.getWeight() > 500 || newDrone.getWeight() < 0;
        if (droneWeightIsNotNegativeOrMoreThanLimit)
            throw new WeightTooLargeException("Weight Cannot be more than 500 or can't be Negative");
        LoadingContainer container = new LoadingContainer();
        List<Medication> medications = new ArrayList<>(20);
        container.setMedications(medications);
        loadingContainerService.storeMedication(container);

        newDrone.setMedicationLoadList(container);

        return droneRepository.save(newDrone);
    }

    @Override
    public List<Drone> findAllDrone() {
        return droneRepository.findAll();
    }

    @Override
    public Drone findDrone(Long id) {
        return droneRepository.findById(id).orElse(null);
    }

    @Override
    public Drone updateDroneStatus(Long id, DroneDto droneDto) throws NonExisitingDroneException {
        if(droneDto == null)
            throw new NullPointerException("Drone needs to be updated as status changes");

        Optional<Drone> foundDrone = droneRepository.findById(id);
        if(foundDrone.isPresent()){
            Drone givenDrone = foundDrone.get();
            droneStateMapper.mapDtoDroneToExistingDrone(droneDto, givenDrone);
            return droneRepository.save(givenDrone);
        }
        else {
            throw new NonExisitingDroneException("Drone with this id " + id + "does not exist");
        }
    }

}
