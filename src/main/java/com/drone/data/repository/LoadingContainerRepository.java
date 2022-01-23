package com.drone.data.repository;

import com.drone.data.model.LoadingContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadingContainerRepository extends JpaRepository<LoadingContainer, Long> {
}
