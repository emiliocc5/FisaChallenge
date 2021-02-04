package com.fisa.TrainsChallenge.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ApiSuccess {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int response;

    private ApiSuccess() {
        timestamp = LocalDateTime.now();
    }

    public ApiSuccess(int response) {
        this();
        this.response = response;
    }

    public int getResponse() {
        return response;
    }
}
