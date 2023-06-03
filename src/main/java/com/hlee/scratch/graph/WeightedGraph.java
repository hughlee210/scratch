package com.hlee.scratch.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import com.hlee.scratch.stack.Stack;

// https://medium.com/basecs/finding-the-shortest-path-with-a-little-help-from-dijkstra-613149fbdc8e
//
public class WeightedGraph<T> {

    List<Vertex> vertices = new ArrayList<>();

    List<Edge> edges = new ArrayList<>();

    // do you need rootVertex?

    // key: Vertex, value: CostInfo with smallest cost and previous vertex
    Map<Vertex, CostInfo> costInfoMap = new HashMap<>();

    static class Vertex {
        String name;
        boolean visited;
        Set<Edge> edges = new HashSet<>();

        public Vertex(String name) {
            this.name = name;
        }

        void addEdge(Edge e) {
            edges.add(e);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Vertex)) {
                return false;
            }
            if (!super.equals(obj)) {
                return false;
            }
            Vertex that = (Vertex) obj;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), name);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Vertex[" + name + "]: ");
            for (Edge e : edges) {
                sb.append(e.toString()).append(", ");
            }
            return sb.toString();
        }
    }

    static class Edge {
        Vertex origin;
        Vertex dest;
        int cost;

        public Edge(Vertex origin, Vertex dest, int cost) {
            this.origin = origin;
            this.origin.addEdge(this);
            this.dest = dest;
            this.dest.addEdge(this);
            this.cost = cost;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Edge)) {
                return false;
            }
            if (!super.equals(obj)) {
                return false;
            }
            return Objects.equals(origin, dest);
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), origin, dest);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Edge[").append(origin.name).append(" -> ").append(dest.name).append(", cost = " + cost + "]");
            return sb.toString();
        }
    }

    static class CostInfo {
        Integer cost = Integer.MAX_VALUE;
        Vertex prevVertex; // vertex from which the cost is calculated

        public CostInfo() {
        }

        public CostInfo(Integer cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "cost: " + cost + ", previous vertex: " + prevVertex;
        }
    }

    public void addVertex(Vertex v) {
        if (!vertices.contains(v)) {
            this.vertices.add(v);
        }
    }

    public void addVertex(String name) {
        Vertex v = new Vertex(name);
        if (!vertices.contains(v)) {
            this.vertices.add(v);
        }
    }

    public void addEdge(Edge e) {
        if (!edges.contains(e)) {
            edges.add(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph[\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i));
            if (i < vertices.size() - 1) {
                sb.append("\n");
            }
        }
        sb.append("\n]");
        return sb.toString();
    }

    public void findSmallestCost(Vertex origin, Vertex dest) {
        if (vertices.contains(origin) && vertices.contains(dest)) {

            Vertex vertex = null;
            // lookup the cost info map and pick up the unvisited vertex with smallest cost

            while ((vertex = lookupSmallestCostVertex()) != null) {
                // for each neighbor or the current vertex, calculate the cost to each neighbor
                // and update the cost info map
                calculateCostToNeighbors(vertex);

                // now we are done calculating the cost to each neighbor of the vertex
                vertex.visited = true;
            }

            // find the smallest cost from origin to destination using stack
            Integer smallestCost = costInfoMap.get(dest).cost;

            // route from origin to destination that produced the smallest cost
            Stack<Vertex> stack = new Stack<>();
            stack.push(dest);
            Vertex prevVertex = costInfoMap.get(dest).prevVertex;
            while (prevVertex != null) {
                stack.push(prevVertex);
                prevVertex = costInfoMap.get(prevVertex).prevVertex;
            }

            StringBuilder str = new StringBuilder();
            while (!stack.isEmpty()) {
                str.append(stack.pop().name + " -> ");
            }

            System.out.println("Smallest cost = " + smallestCost);
            System.out.println("Route = " + str.toString());

        } else {
            throw new NoSuchElementException(origin + " or " + dest + " does not exist");
        }
    }

    Vertex lookupSmallestCostVertex() {
        Integer minCost = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (Map.Entry<Vertex, CostInfo> entry : costInfoMap.entrySet()) {
            Vertex v = entry.getKey();
            if (!v.visited) {
                CostInfo costInfo = entry.getValue();
                if (costInfo.cost < minCost) {
                    minCost = costInfo.cost;
                    minVertex = v;
                }
            }
        }
        return minVertex;
    }

    void calculateCostToNeighbors(Vertex vertex) {
        Integer currentCost = costInfoMap.get(vertex).cost;
        for (Edge e : vertex.edges) {
            Vertex dest = e.dest;
            if (!dest.visited) {
                Integer cost = currentCost + e.cost;
                if (cost < costInfoMap.get(dest).cost) {
                    // update cost info map
                    costInfoMap.get(dest).cost = cost;
                    costInfoMap.get(dest).prevVertex = vertex;
                }
            }
        }
    }

    void initializeCostInfoMap(Vertex root) {
        for (Vertex v : vertices) {
            if (v.equals(root)) {
                costInfoMap.put(v, new CostInfo(0));
            } else {
                costInfoMap.put(v, new CostInfo());
            }
        }
    }

    public static void main(String[] args) {

        WeightedGraph<Integer> graph = new WeightedGraph<>();
        Vertex vA = new Vertex("a");
        Vertex vB = new Vertex("b");
        Vertex vC = new Vertex("c");

        Edge eAC = new Edge(vA, vC, 2);
        Edge eCB = new Edge(vC, vB, 1);
        Edge eAB = new Edge(vA, vB, 5);

        graph.addVertex(vA);
        graph.addVertex(vB);
        graph.addVertex(vC);
        graph.addEdge(eAC);
        graph.addEdge(eCB);
        graph.addEdge(eAB);

        graph.initializeCostInfoMap(vA);

        System.out.println(graph);

        graph.findSmallestCost(vA, vB);

    }

}
