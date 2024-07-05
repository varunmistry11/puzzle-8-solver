package com.example.sharnam.shortestpath;

/**
 * Created by SHARNAM on 01-04-2016.
 */
public class NodeLinks {
    private int source;
    private int destination;
    private int cost;

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getCost() {
        return cost;
    }

    public NodeLinks(int source, int destination, int cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }
}