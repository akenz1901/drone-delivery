package com.drone.service;

import com.drone.data.model.Medication;
import com.drone.data.repository.MedicationRepository;
import com.drone.service.exceptions.InvalidNameFormatException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@Slf4j
@SpringBootTest
class MedicationServiceImplTest {

    @Autowired
    MedicationRepository medicationRepository;
    @Autowired
    MedicationService medicationServiceImpl;

    @BeforeEach
    void setUp() {

    }
    @Test
    void createTempInstance() throws InvalidNameFormatException {
        Medication mistmag = new Medication();

        mistmag.setName("MisMag");
        mistmag.setWeight(70);
        mistmag.setCode("mm6733");

        assertThat(mistmag).isNotNull();
        log.info("Before storing a medication --> {}", mistmag);
        medicationServiceImpl.saveMedication(mistmag);

        Medication dico = new Medication();

        dico.setName("Diclofenac");
        dico.setWeight(150);
        dico.setCode("dcf345");

        assertThat(dico).isNotNull();
        log.info("Before storing a medication --> {}", dico);
        medicationServiceImpl.saveMedication(dico);
    }

    @Test
    void testThatMedicationCanBeSaved() throws InvalidNameFormatException {
        Medication panadol = new Medication();

        panadol.setName("Emzol Panadol");
        panadol.setWeight(100);
        panadol.setCode("pd2302");

        assertThat(panadol).isNotNull();
        log.info("Before storing a medication --> {}", panadol);
        medicationServiceImpl.saveMedication(panadol);
        log.info("After storing a medication --> {}", panadol);
        assertThat(medicationRepository.getById(panadol.getId())).isNotNull();

    }
    @Test
    void testThatOnlyLowerCaseLettersNumbersAndUnderScoreAreAllowedInCode(){
        Medication ibucap = new Medication();

        ibucap.setName("Ibucap");
        ibucap.setWeight(300);
        ibucap.setCode("IBU6302");

        assertThrows(InvalidNameFormatException.class, ()-> medicationServiceImpl.saveMedication(ibucap));

        Medication vitaminC = new Medication();

        vitaminC.setName("vitaminC Orange");
        vitaminC.setWeight(50);
        vitaminC.setCode("vit-#6302");
        assertThrows(InvalidNameFormatException.class, ()-> medicationServiceImpl.saveMedication(ibucap));

    }
    @Test
    void punctuatorsAreNotAllowedInMedicationNameExceptUnderScoreAndDash(){
        Medication ulgicid = new Medication();

        ulgicid.setName("ulgicid620*(");
        ulgicid.setWeight(300);
        ulgicid.setCode("ul65_43");

        assertThrows(InvalidNameFormatException.class, ()-> medicationServiceImpl.saveMedication(ulgicid));
    }
}