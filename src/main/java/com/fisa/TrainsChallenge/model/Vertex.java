package com.fisa.TrainsChallenge.model;

public class Vertex {

    private String vertexName;
    private int distance;

    public Vertex(String vertexName, int distance) {
        this.vertexName = vertexName;
        this.distance = distance;

    }

    public String getVertexName() {
        return vertexName;
    }

    public void setVertexName(String vertexName) {
        this.vertexName = vertexName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
