package com.drone.controller;

import com.drone.data.model.Drone;
import com.drone.data.model.Medication;
import com.drone.service.DroneService;
import com.drone.service.LoadingService;
import com.drone.service.MedicationService;
import com.drone.service.exceptions.LoadingMedicationException;
import com.drone.service.exceptions.NonExisitingDroneException;
import com.drone.service.exceptions.WeightTooLargeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/drone")
public class DroneDeliveryController {

    @Autowired
    DroneService droneServiceImpl;

    @Autowired
    LoadingService loadingServiceImpl;

    @Autowired
    MedicationService medicationServiceImpl;

    @PostMapping("/")
    public ResponseEntity<?> registerDrone(@RequestBody Drone drone) throws WeightTooLargeException {

        return ResponseEntity.ok()
                .body(droneServiceImpl.registerDrone(drone));
    }

    @PostMapping("/{id}/{idMed}")
    @ResponseBody
    public ResponseEntity<?> loadingMedicationOnDrone(@PathVariable Long id, @PathVariable Long idMed, @RequestBody Drone drone) {
        Medication medication = medicationServiceImpl.getMedicationById(idMed);

        try {
            drone = loadingServiceImpl.loadDroneWithMedication(id, medication);
        } catch (LoadingMedicationException | NonExisitingDroneException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok()
                .body(drone);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> checkingLoadedMedications(@PathVariable Long id){
        List<Medication> medicationList =  loadingServiceImpl.loadedMedicationsItems(id);
        return ResponseEntity.ok()
                .body(medicationList);
    }

    @GetMapping("/availableD")
    public ResponseEntity<?> checkingUnIdleDrones(){
        List<Drone> allAvailableDrones = null;
        try {
            allAvailableDrones = loadingServiceImpl.getAvailableDronesForLoading();
        }catch(NonExisitingDroneException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok()
                .body(allAvailableDrones);
    }
    @GetMapping("/percentage/{id}")
    public ResponseEntity<?> checkingGivenDronePercentage(@PathVariable Long id){
        Integer dronePercentage = null;
        try {
            dronePercentage = loadingServiceImpl.checkGivenDroneBattery(id);
        }catch(NonExisitingDroneException e){
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok()
                .body(dronePercentage);
    }

}
