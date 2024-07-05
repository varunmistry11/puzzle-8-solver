package com.example.sharnam.shortestpath;

/**
 * Created by SHARNAM on 01-04-2016.
 */

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by SHARNAM on 28-03-2016.
 */
public class PriorityQueue {
    ArrayList<Node> nodes;

    public PriorityQueue() {
        nodes = new ArrayList<Node>();
    }

    public void add(Node node) {
        nodes.add(node);
        decreaseKey(nodes.size() - 1);
    }

    public Node remove() {
        Collections.swap(nodes, 0, nodes.size() - 1);
        Node retNode = nodes.remove(nodes.size() - 1);
        minHeapify(0);
        return retNode;
    }

    public Node peek() {
        return nodes.get(0);
    }

    public boolean isEmpty() {
        return nodes.isEmpty();
    }

    public void clear() {
        nodes.clear();
    }

    public void decreaseKey(int pos) {
        while (pos > 0 && nodes.get((pos - 1) / 2).getNodePriority() > nodes.get(pos).getNodePriority()) {
            Collections.swap(nodes, pos, (pos - 1) / 2);
            pos = (pos - 1) / 2;
        }
    }

    public int findNodePos(Node node) {
        int i = 0;
        for (Node node1 : nodes) {
            if (node1.equals(node)) {
                break;
            }
            i++;
        }
        return i;
    }

    private void minHeapify(int pos) {
        int left = 2 * pos + 1;
        int right = 2 * pos + 2;
        int smallest = pos;
        if (left < nodes.size() && nodes.get(left).getNodePriority() < nodes.get(pos).getNodePriority()) {
            smallest = left;
        }
        if (right < nodes.size() && nodes.get(right).getNodePriority() < nodes.get(smallest).getNodePriority()) {
            smallest = right;
        }
        if (smallest != pos) {
            Collections.swap(nodes, smallest, pos);
            minHeapify(smallest);
        }
    }
}
