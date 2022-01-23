package com.drone.service.exceptions;

public class NonExistingContainer extends NonExisitingDroneException{
    public NonExistingContainer() {
    }

    public NonExistingContainer(String message) {
        super(message);
    }
}
