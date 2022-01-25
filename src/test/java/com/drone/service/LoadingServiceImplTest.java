package com.drone.service;

import com.drone.data.model.Drone;
import com.drone.data.model.Medication;
import com.drone.data.model.Model;
import com.drone.data.model.Status;
import com.drone.service.exceptions.InvalidNameFormatException;
import com.drone.service.exceptions.LoadingMedicationException;
import com.drone.service.exceptions.NonExisitingDroneException;
import com.drone.service.exceptions.WeightTooLargeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class LoadingServiceImplTest {

    Drone drone1;
    Medication mistMag;
    @Autowired
    MedicationService medicationServiceImpl;

    @Autowired
    LoadingServiceImpl loadingService;

    @Autowired
    DroneServiceImpl droneService;


    @BeforeEach
    void setUp() {
    }

    @Test
    void createInstances() throws WeightTooLargeException{

        drone1 = new Drone();
        drone1.setSerialNumber("D1036");
        drone1.setModel(Model.MIDDLEWEIGHT);
        drone1.setWeight(240);
        drone1.setState(Status.IDLE);
        drone1.setBattery_percentage(45);

        droneService.registerDrone(drone1);

    }


    @Test
    void droneCanBeLoadedWithMedication() throws LoadingMedicationException, NonExisitingDroneException {
//        Medication pill = medicationServiceImpl.getMedicationById(66L);
//
//        assertThat(droneService.findDrone(36L).getState()).isEqualTo(Status.IDLE);
//
//        Drone loadRequest = loadingService.loadDroneWithMedication(36L, pill);
//
//        assertThat(loadRequest).isNotNull();
//
//        assertThat(loadRequest.getMedicationLoadList().getMedications().contains(pill)).isTrue();
//        assertThat(droneService.findDrone(36L).getState()).isEqualTo(Status.LOADED);
    }
    @Test
    void medicationItemsCanBeRetrievedFromASpecificDrone(){
        List<Medication> medications = loadingService.loadedMedicationsItems(31L);

        assertThat(medications).isNotNull();

        assertThat(medications).isNotEmpty();
    }
    @Test
    void checkAvailableDronesAvailableForLoading() throws NonExisitingDroneException {
        List<Drone> idleDrones = loadingService.getAvailableDronesForLoading();

        assertThat(idleDrones).isNotNull();
        assertThat(idleDrones).isNotEmpty();
    }
    @Test
    void checkGiveDroneBattery() throws NonExisitingDroneException {
        Drone givenDrone = droneService.findDrone(33L);

        assertThat(givenDrone).isNotNull();

        assertThat(loadingService.checkGivenDroneBattery(givenDrone.getId())).isEqualTo(70);

    }


}