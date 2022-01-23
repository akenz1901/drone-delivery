package com.drone.service;

import com.drone.data.model.LoadingContainer;
import com.drone.data.model.Medication;
import com.drone.data.model.dto.LoadingContainerDto;
import com.drone.service.exceptions.NonExistingContainer;
import org.springframework.stereotype.Service;

@Service
public interface LoadingContainerService {
    LoadingContainer storeMedication(LoadingContainer loadingContainer);
    LoadingContainer updateLoadingContainer(Long id, LoadingContainerDto loadingContainerDto) throws NonExistingContainer;
}
