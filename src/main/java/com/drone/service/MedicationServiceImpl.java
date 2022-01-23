package com.drone.service;

import com.drone.data.model.Medication;
import com.drone.data.repository.MedicationRepository;
import com.drone.service.exceptions.InvalidNameFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MedicationServiceImpl implements MedicationService{

    @Autowired
    private MedicationRepository medicationRepository;

    @Override
    public Medication saveMedication(Medication newMedication) throws InvalidNameFormatException {
        validateMedicationName(newMedication);
        validateMedicationCode(newMedication);
        return medicationRepository.save(newMedication);
    }

    @Override
    public Medication getMedicationById(Long id) {
        return medicationRepository.findById(id).orElse(null);
    }


    private void validateMedicationName(Medication newMedication) throws InvalidNameFormatException {
        String punctuator = "!?@&#/$&%^(*)+=~`:;|{}'\\>.<,";
        for (String eachChar:newMedication.getName().split("")) {
            if(punctuator.contains(eachChar))
                throw new InvalidNameFormatException("Please use a proper name format, Hint only numbers, letters, underscore and dash are allowed");

        }
    }
    private void validateMedicationCode(Medication medication) throws InvalidNameFormatException {
        String punctuator = "!?@&#/$&%^(*)+=~`:;|-{}'\\>.<,";
        String capLetters = "ABCDEFGSIJKLMNOPQRSTWXYZ";
        for (String eachChar:medication.getCode().split("")) {
            if(punctuator.contains(eachChar))
                throw new InvalidNameFormatException("Please use a proper name format, Hint only numbers,lower letters, underscore are allowed");
        }
        for(String eachChar:medication.getCode().split("")){
            if(capLetters.contains(eachChar))
                throw new InvalidNameFormatException("Uppercase Letter is not allowed");
        }


        }

}
