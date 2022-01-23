package com.drone.service;

import com.drone.data.model.LoadingContainer;
import com.drone.data.model.dto.LoadingContainerDto;
import com.drone.data.repository.LoadingContainerRepository;
import com.drone.service.exceptions.NonExistingContainer;
import com.drone.service.mapping.LoadingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoadingContainerServiceImpl implements LoadingContainerService{

    @Autowired
    LoadingContainerRepository loadingContainerRepository;

    @Autowired
    LoadingMapper loadingMapper;

    @Override
    public LoadingContainer storeMedication(LoadingContainer loadingContainer) {

        return loadingContainerRepository.save(loadingContainer);
    }

    @Override
    public LoadingContainer updateLoadingContainer(Long id, LoadingContainerDto loadingContainerDto) throws NonExistingContainer {
            if(loadingContainerDto == null)
                throw new NullPointerException("Dto can't be Null");

            Optional<LoadingContainer> foundContainer = loadingContainerRepository.findById(id);
            if(foundContainer.isPresent()){
                LoadingContainer gottenContainer = foundContainer.get();
                loadingMapper.mapDtoContainer(loadingContainerDto, gottenContainer);
                return loadingContainerRepository.save(gottenContainer);
            }
            else {
                throw new NonExistingContainer("Non existing container has been passed");
            }
    }
}
