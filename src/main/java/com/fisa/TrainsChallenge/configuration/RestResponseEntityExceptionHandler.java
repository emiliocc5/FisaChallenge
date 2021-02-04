package com.fisa.TrainsChallenge.configuration;

import com.fisa.TrainsChallenge.model.response.ApiError;
import com.fisa.TrainsChallenge.exception.InvalidInputException;
import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ NoSuchRouteException.class, InvalidInputException.class })
    public ResponseEntity<Object> handleNoSuchRouteException(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
