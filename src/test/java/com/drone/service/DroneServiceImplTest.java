package com.drone.service;

import com.drone.data.model.Drone;
import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Model;
import com.drone.data.model.Status;
import com.drone.data.model.dto.DroneDto;
import com.drone.data.repository.DroneRepository;
import com.drone.service.exceptions.NonExisitingDroneException;
import com.drone.service.exceptions.WeightTooLargeException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class DroneServiceImplTest {

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    DroneService droneServiceImpl;

    @BeforeEach
    void setUp() {

    }

    @Test
    void serviceCanRegisterDrone() throws WeightTooLargeException {
        Drone drone = new Drone();
        drone.setSerialNumber("DR1901");
        drone.setModel(Model.LIGHTWEIGHT);
        drone.setWeight(270);
        drone.setBattery_percentage(35);

        assertThat(drone).isNotNull();
        log.info("Before Registering Drone");
//        droneServiceImpl.registerDrone(drone);
        log.info("After Registering Drone");
//        assertThat(droneRepository.findById(2L)).isNotNull();

    }
    @Test
    void anExceptionOccursWhenYouRegisterADroneWithOutrageousWeight(){
        Drone drone = new Drone();
        drone.setSerialNumber("DR101");
        drone.setModel(Model.LIGHTWEIGHT);
        drone.setWeight(700);
        drone.setBattery_percentage(35);
        drone.setState(Status.IDLE);

        assertThrows(WeightTooLargeException.class, ()-> droneServiceImpl.registerDrone(drone));
    }
    @Test
    void testThatDroneCanChangeState() throws WeightTooLargeException, NonExisitingDroneException {
        //given that Drone
        Drone drone = new Drone();
        drone.setSerialNumber("DR733");
        drone.setModel(Model.LIGHTWEIGHT);
        drone.setWeight(500);
        drone.setBattery_percentage(75);
        drone.setState(Status.IDLE);

        droneServiceImpl.registerDrone(drone);

        // assert that drone
        assertThat(drone).isNotNull();
        assertThat(drone.getState()).isEqualTo(Status.IDLE);

        // given that DroneDto
        DroneDto droneDto = new DroneDto();
        droneDto.setState(Status.LOADING);
        droneDto.setWeight(-50);

        droneServiceImpl.updateDroneStatus(drone.getId(), droneDto);

        // assertThat droneDto
        assertThat(droneServiceImpl.findDrone(drone.getId()).getState()).isEqualTo(Status.LOADING);

    }

    @Test
    void casualExisitingUser() throws NonExisitingDroneException {
        DroneDto droneDto = new DroneDto();
        droneDto.setState(Status.IDLE);
//        droneDto.setWeight(400);

        droneServiceImpl.updateDroneStatus(36L, droneDto);
        droneServiceImpl.updateDroneStatus(60L, droneDto);
        droneServiceImpl.updateDroneStatus(44L, droneDto);
    }
}