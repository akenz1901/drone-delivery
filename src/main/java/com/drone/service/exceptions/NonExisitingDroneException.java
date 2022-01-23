package com.drone.service.exceptions;

public class NonExisitingDroneException extends Exception {
    public NonExisitingDroneException() {
    }

    public NonExisitingDroneException(String message) {
        super(message);
    }

    public NonExisitingDroneException(String message, Throwable cause) {
        super(message, cause);
    }
}
