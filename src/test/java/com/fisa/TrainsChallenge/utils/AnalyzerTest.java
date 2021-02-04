package com.fisa.TrainsChallenge.utils;

import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import com.fisa.TrainsChallenge.model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerTest {

    private Analyzer analyzer;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void setUp(){
        this.analyzer = new Analyzer();
    }

    @Test
    public void getDistanceForABCPath() throws NoSuchRouteException {
        String path = "A-B-C";
        Graph graph = getMockedGraph();
        int result = analyzer.getDistance(path.split("-"), graph);
        Assert.assertEquals(9, result);
    }

    @Test
    public void getDistanceForADPath() throws NoSuchRouteException {
        String path = "A-D";
        Graph graph = getMockedGraph();
        int result = analyzer.getDistance(path.split("-"), graph);
        Assert.assertEquals(5, result);
    }

    @Test
    public void getDistanceForADCPath() throws NoSuchRouteException {
        String path = "A-D-C";
        Graph graph = getMockedGraph();
        int result = analyzer.getDistance(path.split("-"), graph);
        Assert.assertEquals(13, result);
    }

    @Test
    public void getDistanceForAEBCDPath() throws NoSuchRouteException {
        String path = "A-E-B-C-D";
        Graph graph = getMockedGraph();
        int result = analyzer.getDistance(path.split("-"), graph);
        Assert.assertEquals(22, result);
    }

    @Test
    public void getDistanceForAEDPath() throws NoSuchRouteException {
        exceptionRule.expect(NoSuchRouteException.class);
        exceptionRule.expectMessage("NO SUCH ROUTE");
        String path = "A-E-D";
        Graph graph = getMockedGraph();
        analyzer.getDistance(path.split("-"), graph);
    }

    //TODO Add all other tests

    @Test
    public void getNumberOfTripsWithAMaximumStopOfThreeFromCToC() {
        String initialPosition = "C";
        String finalPosition = "C";
        int maximumStops = 3;
        Graph graph = getMockedGraph();
        int result = analyzer.getNumberOfTripsWithAMaximumStop(initialPosition, finalPosition, graph, maximumStops);
        Assert.assertEquals(2, result);

    }

    @Test
    public void getNumberOfTripsWithExactlyFourOfStopsFromAToC() {
        String initialPosition = "A";
        String finalPosition = "C";
        int exactStops = 4;
        Graph graph = getMockedGraph();
        int result = analyzer.getNumberOfTripsWithExactlyNumberOfStops(initialPosition, finalPosition, graph, exactStops);
        Assert.assertEquals(3, result);

    }

    @Test
    public void getLengthOfShortestRouteFromAToC() {
        String initialPosition = "A";
        String finalPosition = "C";
        Graph graph = getMockedGraph();
        int dist = analyzer.getLengthOfShortestRoute(initialPosition, finalPosition, graph);
        Assert.assertEquals(9, dist);
    }

    @Test
    public void getLengthOfShortestRouteFromBToB() {
        String initialPosition = "B";
        String finalPosition = "B";
        Graph graph = getMockedGraph();
        int dist = analyzer.getLengthOfShortestRoute(initialPosition, finalPosition, graph);
        Assert.assertEquals(9, dist);
    }

    @Test
    public void getNumberOfRoutesFromCToCWithADistanceLessThanThirty() throws NoSuchRouteException {
        String initialPosition = "C";
        String finalPosition = "C";
        int maximumDistance = 30;
        Graph graph = getMockedGraph();
        int count = analyzer.getNumberOfRoutes(initialPosition, finalPosition, maximumDistance, graph);
        Assert.assertEquals(7, count);
    }

    private Graph getMockedGraph() {
        String graph = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        return new Graph(graph);
    }
}
