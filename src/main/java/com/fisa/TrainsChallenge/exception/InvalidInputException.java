package com.fisa.TrainsChallenge.exception;

import javax.xml.bind.ValidationException;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}
