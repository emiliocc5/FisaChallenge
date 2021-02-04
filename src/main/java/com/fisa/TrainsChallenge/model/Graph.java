package com.fisa.TrainsChallenge.model;

import java.util.*;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyTable;
    private int numberOfVertices;
    private Set<String> vertexes;

    public Graph(String graph) {
        vertexes = new HashSet<>();
        adjacencyTable = new HashMap<>();
        if (null == graph || graph.length() <= 0) {
            return;
        }
        String[] weightedRelations = graph.split(",");
        for (String weightedRelation : weightedRelations) {
            if (weightedRelation.length() != 3) {
                continue;
            }
            String s = String.valueOf(weightedRelation.charAt(0));
            String t = String.valueOf(weightedRelation.charAt(1));
            int w = weightedRelation.charAt(2) - '0';
            Map<String, Integer> sEdgeMap = adjacencyTable.computeIfAbsent(s, k -> new HashMap<>());
            sEdgeMap.put(t, w);
            if (vertexes.add(s)) {
                numberOfVertices++;
            }
            if (vertexes.add(t)) {
                numberOfVertices++;
            }
        }
    }

    public Map<String, Map<String, Integer>> getAdjacencyTable() {
        return adjacencyTable;
    }

    public void setAdjacencyTable(Map<String, Map<String, Integer>> adjacencyTable) {
        this.adjacencyTable = adjacencyTable;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public void setNumberOfVertices(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
    }

    public Set<String> getVertexes() {
        return vertexes;
    }

    public void setVertexes(Set<String> vertexes) {
        this.vertexes = vertexes;
    }
}
