package com.example.sharnam.shortestpath;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by SHARNAM on 01-04-2016.
 */
public class Node {
    private int name;
    private int nodePriority;
    private Node parent;
    HashMap<Integer,Integer> hashMapAdjacentNode;

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void addAdjacent(Node adjacent, int cost) {
        if(!hashMapAdjacentNode.containsKey(adjacent.name)){
            hashMapAdjacentNode.put(adjacent.name,cost);
        }
    }

    public Node(int name) {
        this.name = name;
        nodePriority = 1000;
        hashMapAdjacentNode=new HashMap<Integer,Integer>();
    }

    public int getName() {
        return name;
    }

    public int getNodePriority() {
        return nodePriority;
    }

    public ArrayList<NodeLinks> getAdjacentNodeLinks() {
        ArrayList<NodeLinks> nodeLinkses=new ArrayList<NodeLinks>();
        Iterator it = hashMapAdjacentNode.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            nodeLinkses.add(new NodeLinks(getName(),(int)pair.getKey(),(int)pair.getValue()));
        }
        return nodeLinkses;
    }

    public void setNodePriority(int nodePriority) {
        this.nodePriority = nodePriority;
    }

    @Override
    public boolean equals(Object o) {
        return name==((Node)o).name;
    }
}
