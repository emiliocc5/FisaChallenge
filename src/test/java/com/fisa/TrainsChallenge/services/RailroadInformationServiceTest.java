package com.fisa.TrainsChallenge.services;

import com.fisa.TrainsChallenge.exception.LogicalErrorException;
import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RailroadInformationServiceTest {

    private RailroadInformationService railroadInformationService;

    @Before
    public void setUp(){
        this.railroadInformationService = new RailroadInformationServiceImpl();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void getDistanceForABCPath() throws NoSuchRouteException {
        String path = "A-B-C";
        int result = railroadInformationService.getDistance(path, getStringGraph());
        Assert.assertEquals(9, result);
    }

    @Test
    public void getDistanceForADPath() throws NoSuchRouteException {
        String path = "A-D";
        int result = railroadInformationService.getDistance(path, getStringGraph());
        Assert.assertEquals(5, result);
    }

    @Test
    public void getDistanceForADCPath() throws NoSuchRouteException {
        String path = "A-D-C";
        int result = railroadInformationService.getDistance(path, getStringGraph());
        Assert.assertEquals(13, result);
    }

    @Test
    public void getDistanceForAEBCDPath() throws NoSuchRouteException {
        String path = "A-E-B-C-D";
        int result = railroadInformationService.getDistance(path, getStringGraph());
        Assert.assertEquals(22, result);
    }

    @Test
    public void getDistanceForAEDPath() throws NoSuchRouteException {
        exceptionRule.expect(NoSuchRouteException.class);
        exceptionRule.expectMessage("NO SUCH ROUTE");
        String path = "A-E-D";
        railroadInformationService.getDistance(path, getStringGraph());
    }

    @Test
    public void getNumberOfTripsWithAMaximumStopOfThreeFromCToC() throws LogicalErrorException {
        String initialPosition = "C";
        String finalPosition = "C";
        int maximumStops = 3;
        int result = railroadInformationService.getNumberOfTripsWithAMaximumStop(initialPosition, finalPosition, getStringGraph(), maximumStops);
        Assert.assertEquals(2, result);

    }

    @Test
    public void getNumberOfTripsWithExactlyFourOfStopsFromAToC() throws LogicalErrorException {
        String initialPosition = "A";
        String finalPosition = "C";
        int exactStops = 4;
        int result = railroadInformationService.getNumberOfTripsWithExactlyNumberOfStops(initialPosition, finalPosition, getStringGraph(), exactStops);
        Assert.assertEquals(3, result);

    }

    @Test
    public void getLengthOfShortestRouteFromAToC() throws LogicalErrorException {
        String initialPosition = "A";
        String finalPosition = "C";
        int dist = railroadInformationService.getLengthOfShortestRoute(initialPosition, finalPosition, getStringGraph());
        Assert.assertEquals(9, dist);
    }

    @Test
    public void getLengthOfShortestRouteFromBToB() throws LogicalErrorException {
        String initialPosition = "B";
        String finalPosition = "B";
        int dist = railroadInformationService.getLengthOfShortestRoute(initialPosition, finalPosition, getStringGraph());
        Assert.assertEquals(9, dist);
    }

    @Test
    public void getNumberOfRoutesFromCToCWithADistanceLessThanThirty() throws NoSuchRouteException, LogicalErrorException {
        String initialPosition = "C";
        String finalPosition = "C";
        int maximumDistance = 30;
        int count = railroadInformationService.getNumberOfRoutes(initialPosition, finalPosition, maximumDistance, getStringGraph());
        Assert.assertEquals(7, count);
    }

    private String getStringGraph() {
        return "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    }
}
