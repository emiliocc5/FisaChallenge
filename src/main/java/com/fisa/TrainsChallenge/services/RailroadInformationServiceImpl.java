package com.fisa.TrainsChallenge.services;

import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import com.fisa.TrainsChallenge.model.Graph;
import com.fisa.TrainsChallenge.utils.Analyzer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RailroadInformationServiceImpl implements RailroadInformationService {

    private final Logger log = LoggerFactory.getLogger(RailroadInformationServiceImpl.class);

    private final Analyzer analyzer;

    public RailroadInformationServiceImpl() {
        this.analyzer = new Analyzer();
    }

    @Override
    public int getDistance(String path, String stringGraph) throws NoSuchRouteException {
        log.info("Creating graph from: {}", stringGraph);
        Graph graph = new Graph(stringGraph);
        log.info("Getting distance for {}", path);
        return analyzer.getDistance(path.split("-"), graph);
    }

    @Override
    public int getNumberOfTripsWithAMaximumStop(String initialPosition, String finalPosition, String stringGraph, int maximumStop) {
        log.info("Creating graph from: {}", stringGraph);
        Graph graph = new Graph(stringGraph);
        log.info("Getting number of trips from {} to {} with a maximum stop {}", initialPosition, finalPosition, maximumStop);
        return analyzer.getNumberOfTripsWithAMaximumStop(initialPosition, finalPosition, graph, maximumStop);
    }

    @Override
    public int getNumberOfTripsWithExactlyNumberOfStops(String initialPosition, String finalPosition, String stringGraph, int stops) {
        log.info("Creating graph from: {}", stringGraph);
        Graph graph = new Graph(stringGraph);
        log.info("Getting number of trips from {} to {} with exactly {} stops", initialPosition, finalPosition, stops);
        return analyzer.getNumberOfTripsWithExactlyNumberOfStops(initialPosition, finalPosition, graph, stops);
    }

    @Override
    public int getLengthOfShortestRoute(String initialPosition, String finalPosition, String stringGraph) {
        log.info("Creating graph from: {}", stringGraph);
        Graph graph = new Graph(stringGraph);
        log.info("Getting length of short route from {} to {} ", initialPosition, finalPosition);
        return analyzer.getLengthOfShortestRoute(initialPosition, finalPosition, graph);
    }

    @Override
    public int getNumberOfRoutes(String initialPosition, String finalPosition, int maximumDistance, String stringGraph) throws NoSuchRouteException {
        log.info("Creating graph from: {}", stringGraph);
        Graph graph = new Graph(stringGraph);
        log.info("Getting number of routes from {} to {} with less or equal than {} distance", initialPosition, finalPosition, maximumDistance);
        return analyzer.getNumberOfRoutes(initialPosition, finalPosition, maximumDistance, graph);
    }
}
