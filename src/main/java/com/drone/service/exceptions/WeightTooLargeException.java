package com.drone.service.exceptions;

public class WeightTooLargeException extends Exception{
    public WeightTooLargeException() { }

    public WeightTooLargeException(String message) {
        super(message);
    }

    public WeightTooLargeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeightTooLargeException(Throwable cause) {
        super(cause);
    }
}
