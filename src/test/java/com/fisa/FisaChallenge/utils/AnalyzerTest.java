package com.fisa.FisaChallenge.utils;

import com.fisa.FisaChallenge.exception.NoSuchRouteException;
import com.fisa.FisaChallenge.model.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnalyzerTest {

    private Analyzer analyzer;

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

    //TODO Add all other tests

    @Test
    public void getNumberOfTripsWithAMaximumStop() {
        Graph graph = getMockedGraph();
        int result = analyzer.getNumberOfTripsWithAMaximumStop("C", "C", graph, 4);
        Assert.assertEquals(2, result);

    }

    @Test
    public void getNumberOfTripsWithExactlyNumberOfStops() {
        Graph graph = getMockedGraph();
        int result = analyzer.getNumberOfTripsWithAMaximumStop("A", "C", graph, 4);
        Assert.assertEquals(3, result);

    }

    @Test
    public void getLengthOfShortestRoute() {
        Graph graph = getMockedGraph();
        int dist = analyzer.getLengthOfShortestRoute("A", "C", graph);
        Assert.assertEquals(9, dist);
    }

    @Test
    public void getNumberOfRoutes() throws NoSuchRouteException {
        Graph graph = getMockedGraph();
        int count = analyzer.getNumberOfRoutes("C", "C" , 30, graph);
        Assert.assertEquals(7, count);
    }

    private Graph getMockedGraph() {
        String graph = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        return new Graph(graph);
    }
}
