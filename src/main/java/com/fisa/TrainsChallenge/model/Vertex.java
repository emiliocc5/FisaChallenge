package com.fisa.TrainsChallenge.model;

//TODO change names
public class Vertex {
    public String v;
    public int dist;//distance of start vertex and v

    public Vertex(String v, int dist) {
        this.v = v;
        this.dist = dist;

    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
