package com.drone.data.repository;

import com.drone.data.model.Medication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class MedicationRepositoryTest {

    @Autowired
    MedicationRepository medicationRepository;

    @BeforeEach
    void setUp() {

    }
    @Test
    void repositoryCanSaveAMedication(){
        Medication medication = new Medication();

        medication.setName("Paracetamol");
        medication.setWeight(40);
        medication.setCode("PR3040");

        assertThat(medication).isNotNull();
        log.info("Before storing a medication --> {}", medication);
        medicationRepository.save(medication);
        log.info("After storing a medication --> {}", medication);
        assertThat(medicationRepository.getById(medication.getId())).isNotNull();
    }
}