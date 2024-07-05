package com.example.sharnam.shortestpath;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by SHARNAM on 01-04-2016.
 */
public class Graph {
    private ArrayList<Node> nodes;
    private HashMap<Integer,ArrayList<NodeLinks>> allNodeLinks;
    private Node sourceNode;
    private Node destinationNode;
    private Random random = new Random();

    public Graph() {
        nodes = new ArrayList<Node>();
        allNodeLinks = new HashMap<Integer,ArrayList<NodeLinks>>();

        for (int i = 1; i <= 9; i++) {
            nodes.add(new Node(i));
        }
        sourceNode = nodes.get(0);
        destinationNode = nodes.get(8);

        int i = 0;
        int count = 0;
        while (count < 4 && i!=8) {

            int gen = random.nextInt(9);
            if(count<4 && gen==8){
                continue;
            }
            if (gen != i) {
                if(count==3){
                    gen=8;
                }
                nodes.get(i).addAdjacent(nodes.get(gen), random.nextInt(10)+1);
                Log.e("COUNTING ", i+" ATTACHED TO "+gen);
                i = gen;
                count++;
            }
        }
        Log.e("Exiting", "Exiting");
        count=random.nextInt(4)+1;
        int index=0;
        while(index<count){
            int src=random.nextInt(9);
            int dest=random.nextInt(9);
            int cost=random.nextInt(10)+1;
            if(src!=dest){
                index++;
                nodes.get(src).addAdjacent(nodes.get(dest),cost);
            }
        }

        for (Node n : nodes) {
            if (!n.getAdjacentNodeLinks().isEmpty()) {
                allNodeLinks.put(n.getName(),n.getAdjacentNodeLinks());
            }
        }
    }

    public HashMap<Integer,ArrayList<NodeLinks>>  getAllNodeLinks() {
        return allNodeLinks;
    }

    public String dijkstraAlgorithm() {
        String solution = "";
        PriorityQueue priorityQueue = new PriorityQueue();

        sourceNode.setNodePriority(0);

        for (Node n : nodes) {
            priorityQueue.add(n);
        }

        priorityQueue.add(sourceNode);

        while (!priorityQueue.isEmpty()) {

            Node u = priorityQueue.remove();
            ArrayList<NodeLinks> adjacents = allNodeLinks.get(u.getName());
				if(adjacents!=null)
            for (NodeLinks adjacent : adjacents) {
                Node src = nodes.get(u.getName()-1);
                Node dest = nodes.get(adjacent.getDestination()-1);

                if (adjacent.getCost() + src.getNodePriority() < dest.getNodePriority()) {
                    dest.setNodePriority(src.getNodePriority() + adjacent.getCost());
                    dest.setParent(src);
                    priorityQueue.decreaseKey(priorityQueue.findNodePos(dest));
                }
            }
        }

        Node pointer = destinationNode;
        while (pointer != null) {
            solution = pointer.getName() + solution;
            pointer = pointer.getParent();
            if (pointer != null) {
                solution = "-" + solution;
            }
        }
        return solution;
    }

}