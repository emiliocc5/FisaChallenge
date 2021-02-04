package com.fisa.TrainsChallenge.services;

import com.fisa.TrainsChallenge.exception.LogicalErrorException;
import com.fisa.TrainsChallenge.exception.NoSuchRouteException;

public interface RailroadInformationService {

    public int getDistance(String path, String stringGraph) throws NoSuchRouteException;
    public int getNumberOfTripsWithAMaximumStop(String initialPosition, String finalPosition, String stringGraph, int maximumStop) throws LogicalErrorException;
    public int getNumberOfTripsWithExactlyNumberOfStops(String initialPosition, String finalPosition, String stringGraph, int stops) throws LogicalErrorException;
    public int getLengthOfShortestRoute(String initialPosition, String finalPosition, String stringGraph) throws LogicalErrorException;
    public int getNumberOfRoutes(String initialPosition, String finalPosition, int maximumDistance, String stringGraph) throws NoSuchRouteException, LogicalErrorException;
}
