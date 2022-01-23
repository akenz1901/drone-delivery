package com.drone.service;

import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Medication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoadingContainerServiceImplTest {

    @Autowired
    LoadingContainerService loadingContainerServiceImpl;

    @Autowired
    MedicationService medicationServiceImpl;

    @BeforeEach
    void setUp() {

    }

    @Test
    void containerCanBeLoaded(){
        LoadingContainer container = new LoadingContainer();

        assertThat(container.getMedications()).hasSize(0);

        Medication foundMedication = medicationServiceImpl.getMedicationById(1L);
        List<Medication> medicationList = new ArrayList<>();

        medicationList.add(foundMedication);


        container.setMedications(medicationList);
        assertThat(container.getMedications()).hasSize(1);

        LoadingContainer stored = loadingContainerServiceImpl.storeMedication(container);

        assertThat(stored.getMedications()).hasSize(1);


    }
}