package com.fisa.TrainsChallenge.model;

import java.util.*;


//TODO refactor this class
//TODO Al armar el grafo, verificar que no haya blancos

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyTable;  // adjacency table, store the relationship of each vertex
    private int numberOfVertices;  //number of vertices
    private Set<String> vertexes;  //all vertex collections

    public Graph(String graph){
        vertexes = new HashSet<>();
        adjacencyTable = new HashMap<>();
        if (null == graph || graph.length() <=0 ){
            return;
        }
        //TODO replace vertexLenghts with weightedRelations
        String[] vertexLengths = graph.split(",");
        for (String vertexLength : vertexLengths) {
            if (vertexLength.length() != 3) {
                continue;
            }
            String s = String.valueOf(vertexLength.charAt(0));
            String t = String.valueOf(vertexLength.charAt(1));
            int w = vertexLength.charAt(2) - '0';
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
