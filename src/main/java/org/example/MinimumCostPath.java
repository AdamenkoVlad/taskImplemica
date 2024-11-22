package org.example;

import java.util.*;

/*

Input:
        1
        4
gdansk
2
        2 1
        3 3
bydgoszcz
3
        1 1
        3 1
        4 4
torun
3
        1 3
        2 1
        4 1
warszawa
2
        2 4
        3 1
        2
gdansk warszawa
bydgoszcz warszawa

*/

public class MinimumCostPath {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of test cases
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            int cityCount = scanner.nextInt();
            Map<String, Integer> cityIndices = new HashMap<>();
            List<List<Edge>> graph = new ArrayList<>();

            // Reading cities and graph
            for (int i = 0; i < cityCount; i++) {
                String cityName = scanner.next();
                cityIndices.put(cityName, i);
                int neighborsCount = scanner.nextInt();
                graph.add(new ArrayList<>());

                for (int j = 0; j < neighborsCount; j++) {
                    int neighborIndex = scanner.nextInt() - 1;
                    int cost = scanner.nextInt();
                    graph.get(i).add(new Edge(neighborIndex, cost));
                }
            }

            // Number of queries
            int queryCount = scanner.nextInt();
            for (int q = 0; q < queryCount; q++) {
                String source = scanner.next();
                String destination = scanner.next();

                int sourceIndex = cityIndices.get(source);
                int destinationIndex = cityIndices.get(destination);
                int result = dijkstra(graph, sourceIndex, destinationIndex);
                System.out.println(result);
            }

            // Skip empty line between tests
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
        }
    }

    // Dixtra's algorithm to find the minimum path cost
    public static int dijkstra(List<List<Edge>> graph, int source, int destination) {
        int[] minCosts = new int[graph.size()];
        Arrays.fill(minCosts, Integer.MAX_VALUE);
        minCosts[source] = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        priorityQueue.add(new Edge(source, 0));

        while (!priorityQueue.isEmpty()) {
            Edge current = priorityQueue.poll();

            if (current.index == destination) {
                return current.cost;
            }

            for (Edge neighbor : graph.get(current.index)) {
                int newCost = current.cost + neighbor.cost;
                if (newCost < minCosts[neighbor.index]) {
                    minCosts[neighbor.index] = newCost;
                    priorityQueue.add(new Edge(neighbor.index, newCost));
                }
            }
        }

        return -1; // If no path exists
    }
}

// Class Edge
class Edge {
    int index;
    int cost;

    Edge(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}
