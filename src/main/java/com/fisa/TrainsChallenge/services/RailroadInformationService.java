package com.fisa.TrainsChallenge.services;

import com.fisa.TrainsChallenge.exception.NoSuchRouteException;

import java.util.List;

public interface RailroadInformationService {

    public int getDistance(String path, String stringGraph) throws NoSuchRouteException;
    public int getNumberOfTripsWithAMaximumStop(String initialPosition, String finalPosition, String stringGraph, int maximumStop);
    public int getNumberOfTripsWithExactlyNumberOfStops(String initialPosition, String finalPosition, String stringGraph, int stops);
    public int getLengthOfShortestRoute(String initialPosition, String finalPosition, String stringGraph);
    public int getNumberOfRoutes(String initialPosition, String finalPosition, int maximumDistance, String stringGraph) throws NoSuchRouteException;
}
