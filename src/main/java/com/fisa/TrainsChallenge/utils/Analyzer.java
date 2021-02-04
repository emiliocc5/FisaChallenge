package com.fisa.TrainsChallenge.utils;

import com.fisa.TrainsChallenge.exception.NoSuchRouteException;
import com.fisa.TrainsChallenge.model.Graph;
import com.fisa.TrainsChallenge.model.Vertex;
import org.springframework.http.HttpStatus;

import java.util.*;

public class Analyzer {

    private int trips;

    public int getDistance(String[] vertexes, Graph graph) throws NoSuchRouteException {
        String actualVertex = vertexes[0];
        int distance = 0;
        for (int i = 1; i < vertexes.length; i++) {
            String nextVertex = vertexes[i];
            Map<String, Integer> edgeMap = graph.getAdjacencyTable().get(actualVertex);
            if (null != edgeMap && edgeMap.containsKey(nextVertex)) {
                distance += edgeMap.get(nextVertex);
            } else {
                throw new NoSuchRouteException("NO SUCH ROUTE");
            }
            actualVertex = nextVertex;
        }
        return distance;
    }

    public int getNumberOfTripsWithAMaximumStop(String initialPosition, String finalPosition, Graph graph, int maximumStops) {
        List<Object[]> paths = getPathsOfTwoVertex(initialPosition, finalPosition, graph);
        int count = 0;
        for (Object[] path : paths) {
            if (path.length <= maximumStops) {
                count++;
            }
        }
        return count;
    }

    public int getNumberOfTripsWithExactlyNumberOfStops(String initialPosition, String finalPosition, Graph graph, int maximumStops) {
        List<Object[]> paths = getPathsOfTwoVertex(initialPosition, finalPosition, graph);
        int count = 0;
        for (Object[] path : paths) {
            if (path.length == maximumStops) {
                count++;
            }
        }
        return count;
    }

    private List<Object[]> getPathsOfTwoVertex(String initialPosition, String finalPosition, Graph graph) {
        Stack<String> stack = new Stack<>();
        List<Object[]> paths = new LinkedList<>();
        Set<String> visited = new HashSet<>(graph.getNumberOfVertices());
        dfsPath(initialPosition, finalPosition, null, stack, paths, visited, graph);
        return paths;
    }

    private void dfsPath(String index, String end, String prev, Stack<String> stack, List<Object[]> paths, Set<String> visited, Graph graph) {
        stack.push(index);
        if (index.equals(end) && prev != null) {
            paths.add(stack.toArray());
            stack.pop();
        } else {
            Map<String, Integer> edgeMap = graph.getAdjacencyTable().get(index);
            if (null != edgeMap && edgeMap.size() > 0) {
                for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
                    if (!stack.contains(entry.getKey()) || !visited.contains(entry.getKey())) {
                        dfsPath(entry.getKey(), end, index, stack, paths, visited, graph);
                    }
                }
                visited.add(stack.pop());
            }
        }
    }

    public int getLengthOfShortestRoute(String initialPosition, String finalPosition, Graph graph) {
        Map<String, Vertex> parentMap = new HashMap<>(graph.getNumberOfVertices());//store the vertex and distance closest to each vertex
        for (String vertex : graph.getVertexes()) {
            parentMap.put(vertex, new Vertex(initialPosition, Integer.MAX_VALUE));
        }
        parentMap.put(initialPosition, null);
        Queue<Vertex> pqueue = new PriorityQueue<>((Comparator) (o1, o2) -> {
            Vertex v1 = (Vertex) o1;
            Vertex v2 = (Vertex) o2;
            return v1.dist - v2.dist;
        });
        pqueue.add(new Vertex(initialPosition, 0));
        Set<String> visited = new HashSet<>(graph.getNumberOfVertices());
        while (!pqueue.isEmpty()) {
            Vertex minVertex = pqueue.poll();
            if (!visited.add(minVertex.v)) {
                continue;
            }
            Map<String, Integer> edgeMap = graph.getAdjacencyTable().get(minVertex.v);
            for (Map.Entry<String, Integer> entry : edgeMap.entrySet()) {
                int dist = minVertex.dist + entry.getValue();
                if (null != parentMap.get(entry.getKey()) &&
                        dist < parentMap.get(entry.getKey()).dist) {//The distance from the starting vertex is smaller than the previous one. The previous vertex of the updated shortest path is the vertex just dequeued from the priority queue.
                    parentMap.get(entry.getKey()).dist = dist;
                    parentMap.get(entry.getKey()).v = minVertex.v;
                }

                pqueue.add(new Vertex(entry.getKey(), dist));
            }
        }
        if (!initialPosition.equals(finalPosition)) {
            return parentMap.get(finalPosition).dist;
        } else {
            return calculateDist(initialPosition, parentMap, graph);
        }
    }

    private int calculateDist(String s, Map<String, Vertex> parentMap, Graph graph) {
        int dist = Integer.MAX_VALUE;
        for (Map.Entry<String, Vertex> entry : parentMap.entrySet()) {
            if (entry.getValue() != null
                    && entry.getValue().v.equals(s)
                    && entry.getValue().dist < dist) {
                String next = entry.getKey();//First find the vertex closest to the target vertex
                int curDist = entry.getValue().dist + getLengthOfShortestRoute(next, s, graph);
                if (curDist < dist) {
                    dist = curDist;
                }
            }
        }
        return dist;
    }

    public int getNumberOfRoutes(String start, String end, int maxDistance, Graph graph) throws NoSuchRouteException {
        LinkedList<String> visited = new LinkedList<>();
        trips = 0;
        visited.add(start);
        getNumberOfRoutes(end, visited, maxDistance, graph);
        return trips;
    }

    private void getNumberOfRoutes(String end, LinkedList<String> visited, int maxDistance, Graph graph) throws NoSuchRouteException {
        Set<String> nodes = graph.getAdjacencyTable().get((visited.getLast())).keySet();
        boolean maxPathReached = false;
        for (String node : nodes) {
            if (node.equals(end)) {
                visited.add(node);
                int routedDistance =  getDistance(visited.toArray(new String[0]), graph);
                if (routedDistance < maxDistance) {
                    trips += 1;
                } else {
                    maxPathReached = true;
                }

                visited.removeLast();
                break;
            }
        }
        for (String node : nodes) {
            if (maxPathReached) {
                continue;
            }
            visited.addLast(node);
            getNumberOfRoutes(end, visited, maxDistance, graph);
            visited.removeLast();
        }

    }
}
