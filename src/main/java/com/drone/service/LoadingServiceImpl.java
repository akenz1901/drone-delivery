package com.drone.service;

import com.drone.data.model.Drone;
import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Medication;
import com.drone.data.model.Status;
import com.drone.data.model.dto.DroneDto;
import com.drone.data.model.dto.LoadingContainerDto;
import com.drone.service.exceptions.LoadingMedicationException;
import com.drone.service.exceptions.LowBatteryExcpetion;
import com.drone.service.exceptions.NonExisitingDroneException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class LoadingServiceImpl implements LoadingService{

    @Autowired
    private DroneServiceImpl droneService;

    @Autowired
    private LoadingContainerService loadingContainerService;



    @Override
    public Drone loadDroneWithMedication(Long droneId, Medication medication) throws LoadingMedicationException, NonExisitingDroneException {
        LoadingContainer loadingContainer = new LoadingContainer();
        List<Medication> medicationList = new ArrayList<>();
        LoadingContainerDto loadingContainerDto = new LoadingContainerDto();
        DroneDto droneDto = new DroneDto();

        Drone existingDrone = droneService.findDrone(droneId);
        log.info("Drone was --> {}", existingDrone.getState());

        medicationList.add(medication);
        validateBattery(droneId);
        validateDroneStatus(droneId);
        int newSize = reduceWeight(existingDrone, medication);
        droneDto.setWeight(newSize);
        droneDto.setState(Status.LOADING);
        List<Medication> availableMedications = existingDrone.getMedicationLoadList().getMedications();

        if(availableMedications.isEmpty()) {
            droneDto.setContainer(loadingContainer);
            droneDto.getContainer().setMedications(medicationList);
            loadingContainerDto.setMedications(medicationList);
            droneDto.setState(Status.LOADED);
            log.info("Is empty and is --> {} here", existingDrone.getState());
            loadingContainerService.updateLoadingContainer(existingDrone.getMedicationLoadList().getId(), loadingContainerDto);
            return droneService.updateDroneStatus(existingDrone.getId(), droneDto);
        }
        else {
            medicationList.addAll(availableMedications);
            loadingContainer.setMedications(medicationList);
            droneDto.setContainer(loadingContainer);
            loadingContainerDto.setMedications(medicationList);
            droneDto.setState(Status.LOADED);
            log.info("Loading in second statement and is --> {} here", existingDrone.getState());
            loadingContainerService.updateLoadingContainer(existingDrone.getMedicationLoadList().getId(), loadingContainerDto);
            return droneService.updateDroneStatus(existingDrone.getId(), droneDto);
        }

    }

    @Override
    public List<Medication> loadedMedicationsItems(Long droneId) {
        Drone drone = droneService.findDrone(droneId);
        if(drone.getMedicationLoadList() != null | !drone.getMedicationLoadList().getMedications().isEmpty())
            return drone.getMedicationLoadList().getMedications();
        else
            throw new NullPointerException("This drone doesn't have a container or It's Empty");
    }

    @Override
    public List<Drone> getAvailableDronesForLoading() throws NonExisitingDroneException {
        List<Drone> allIdleDrones = new ArrayList<>();
        List<Drone> allDrones = droneService.findAllDrone();
        if(!allDrones.isEmpty()){
            for (Drone drone:allDrones){
                if(drone.getState().equals(Status.IDLE) & drone.getState() != null)
                    allIdleDrones.add(drone);
                }
            return allIdleDrones;}
        else if(allIdleDrones.isEmpty())
            throw new NonExisitingDroneException("No drone is IDLE");
        else
            throw new NonExisitingDroneException("Drone is absolutely empty");
    }

    @Override
    public Integer checkGivenDroneBattery(Long id) throws NonExisitingDroneException {
        Drone givenDrone = droneService.findDrone(id);
        return givenDrone.getBattery_percentage();
    }

    private void validateBattery(Long id) throws NonExisitingDroneException {
       Integer batteryLevel = checkGivenDroneBattery(id);
       if(batteryLevel <= 25)
           throw new LowBatteryExcpetion("This drone battery is low pls Use another available drone");
    }
    private void validateDroneStatus(Long id) throws NonExisitingDroneException {
        Drone searchedDrone = droneService.findDrone(id);
        if(!searchedDrone.getState().equals(Status.IDLE))
            throw new NonExisitingDroneException("Drone is not Idle check for available drones");
    }

    private int reduceWeight(Drone drone, Medication medication) throws LoadingMedicationException {
        int newWeight;
        int droneWeight = drone.getWeight();
        int medicationWeight = medication.getWeight();
        if(medicationWeight <= droneWeight) {
            newWeight = drone.getWeight() - medication.getWeight();
            return newWeight;
        }
        else
            throw new LoadingMedicationException("Insuffient space or Drone is full");
    }
    public List<Drone> checkReturnedDrones(){

        // TODO TODO TODO TODO
        return null;
    }
    public List<Drone> checkDeliveredDrones(){

        // TODO TODO TODO TODO
        return null;
    }

}
