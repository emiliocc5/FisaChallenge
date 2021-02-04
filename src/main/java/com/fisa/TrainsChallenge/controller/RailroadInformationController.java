package com.fisa.TrainsChallenge.controller;

import com.fisa.TrainsChallenge.exception.InvalidInputException;
import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import com.fisa.TrainsChallenge.model.response.ApiSuccess;
import com.fisa.TrainsChallenge.services.RailroadInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/railroad-information")
public class RailroadInformationController {

    @Autowired
    private RailroadInformationService railroadInformationService;

    @PostMapping(value = "/distance")
    public ResponseEntity<Object> getDistance(@RequestBody Map<String, String> req) throws NoSuchRouteException, InvalidInputException {
        String path = req.get("path");
        if (path == null) {
            throw new InvalidInputException("Path parameter cannot be null");
        }
        String graph = req.get("graph");
        if (graph == null) {
            throw new InvalidInputException("Graph parameter cannot be null");
        }
        ApiSuccess apiSuccess = new ApiSuccess(railroadInformationService.getDistance(path, graph));
        return buildSuccessfulResponseEntity(apiSuccess);
    }

    @PostMapping(value = "/number-of-trips-with-maximum-stop")
    public ResponseEntity<Object> getNumberOfTripsWithAMaximumStop(@RequestBody Map<String, String> req) throws InvalidInputException {
        String initialPosition = req.get("initialPosition");
        if (initialPosition == null) {
            throw new InvalidInputException("Initial Position parameter cannot be null");
        }
        String finalPosition = req.get("finalPosition");
        if (finalPosition == null) {
            throw new InvalidInputException("Final Position parameter cannot be null");
        }

        String graph = req.get("graph");
        if (graph == null) {
            throw new InvalidInputException("Graph parameter cannot be null");
        }

        String maximumStop = req.get("maximumStop");
        if (maximumStop == null) {
            throw new InvalidInputException("Maximum Stop parameter cannot be null");
        }
        ApiSuccess apiSuccess = new ApiSuccess(railroadInformationService.getNumberOfTripsWithAMaximumStop(initialPosition, finalPosition, graph, Integer.parseInt(maximumStop)));
        return buildSuccessfulResponseEntity(apiSuccess);
    }

    @PostMapping(value = "/number-of-trips-with-exactly-stops")
    public ResponseEntity<Object> getNumberOfTripsWithExactlyNumberOfStops(@RequestBody Map<String, String> req) throws InvalidInputException {
        String initialPosition = req.get("initialPosition");
        if (initialPosition == null) {
            throw new InvalidInputException("Initial Position parameter cannot be null");
        }
        String finalPosition = req.get("finalPosition");
        if (finalPosition == null) {
            throw new InvalidInputException("Final Position parameter cannot be null");
        }
        String graph = req.get("graph");
        if (graph == null) {
            throw new InvalidInputException("Graph parameter cannot be null");
        }
        String stops = req.get("stops");
        if (stops == null) {
            throw new InvalidInputException("Maximum Stop parameter cannot be null");
        }
        ApiSuccess apiSuccess = new ApiSuccess(railroadInformationService.getNumberOfTripsWithExactlyNumberOfStops(initialPosition, finalPosition, graph, Integer.parseInt(stops)));
        return buildSuccessfulResponseEntity(apiSuccess);
    }

    @PostMapping(value = "/short-distance")
    public ResponseEntity<Object> getLengthOfShortestRoute(@RequestBody Map<String, String> req) throws InvalidInputException {
        String initialPosition = req.get("initialPosition");
        if (initialPosition == null) {
            throw new InvalidInputException("Initial Position parameter cannot be null");
        }
        String finalPosition = req.get("finalPosition");
        if (finalPosition == null) {
            throw new InvalidInputException("Final Position parameter cannot be null");
        }
        String graph = req.get("graph");
        if (graph == null) {
            throw new InvalidInputException("Graph parameter cannot be null");
        }
        ApiSuccess apiSuccess = new ApiSuccess(railroadInformationService.getLengthOfShortestRoute(initialPosition, finalPosition, graph));
        return buildSuccessfulResponseEntity(apiSuccess);
    }

    @PostMapping(value = "/number-of-routes")
    public ResponseEntity<Object> getNumberOfRoutes(@RequestBody Map<String, String> req) throws InvalidInputException, NoSuchRouteException {
        String initialPosition = req.get("initialPosition");
        if (initialPosition == null) {
            throw new InvalidInputException("Initial Position parameter cannot be null");
        }
        String finalPosition = req.get("finalPosition");
        if (finalPosition == null) {
            throw new InvalidInputException("Final Position parameter cannot be null");
        }
        String graph = req.get("graph");
        if (graph == null) {
            throw new InvalidInputException("Graph parameter cannot be null");
        }
        String maximumDistance = req.get("maximumDistance");
        if (maximumDistance == null) {
            throw new InvalidInputException("Maximum distance parameter cannot be null");
        }
        ApiSuccess apiSuccess = new ApiSuccess(railroadInformationService.getNumberOfRoutes(initialPosition, finalPosition, Integer.parseInt(maximumDistance),graph));
        return buildSuccessfulResponseEntity(apiSuccess);
    }

    private ResponseEntity<Object> buildSuccessfulResponseEntity(ApiSuccess apiSuccess) {
        return new ResponseEntity<>(apiSuccess, HttpStatus.OK);
    }
}
