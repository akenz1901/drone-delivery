package com.drone.service.exceptions;

public class LoadingMedicationException extends Exception {
    public LoadingMedicationException() {
    }

    public LoadingMedicationException(String message) {
        super(message);
    }

    public LoadingMedicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
