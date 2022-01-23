package com.drone.service;

import com.drone.data.model.Medication;
import com.drone.service.exceptions.InvalidNameFormatException;
import org.springframework.stereotype.Service;

@Service
public interface MedicationService {

    Medication saveMedication(Medication newMedication) throws InvalidNameFormatException;
    Medication getMedicationById(Long id);

}
