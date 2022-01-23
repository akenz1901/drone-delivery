package com.drone.data.repository;

import com.drone.data.model.Drone;
import com.drone.data.model.Model;
import com.drone.data.model.Status;
import com.drone.data.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
class DroneRespositoryTest {

    @Autowired
    DroneRepository droneRepository;

    @BeforeEach
    void setUp() {

    }
    @Test
    void repositoryCanSaveRegisteredDrone(){
        Drone drone = new Drone();
        drone.setSerialNumber("DR100");
        drone.setModel(Model.HEAVYWEIGHT);
        drone.setBattery_percentage(70);
        drone.setWeight(430);
        drone.setState(Status.IDLE);

        assertThat(drone).isNotNull();
        log.info("Drone before registration --> {}", drone);
        droneRepository.save(drone);
        assertThat(drone.getId()).isNotNull();
        assertThat(droneRepository.findById(1L)).isNotNull();
        log.info("After saving Drone --> {}", drone);
    }
}