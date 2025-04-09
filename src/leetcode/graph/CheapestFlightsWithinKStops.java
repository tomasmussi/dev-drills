package leetcode.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class CheapestFlightsWithinKStops {

    public static void tests() {
        var v = new CheapestFlightsWithinKStops();
        //[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]
        System.out.println(v.findCheapestPrice(4, new int[][] {
                {0, 1, 100},
                {1, 2, 100},
                {2, 0, 100},
                {1, 3, 600},
                {2, 3, 200},
        }, 0, 3, 1));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> adjacency = new HashMap<>();

        for (int[] flight : flights) {
            int from = flight[0];
            int to = flight[1];
            int cost = flight[2];
            adjacency.putIfAbsent(from, new HashMap<>());
            adjacency.putIfAbsent(to, new HashMap<>());
            adjacency.get(from).put(to, cost);
        }
        int[] distances = new int[n];
        int[] steps = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(steps, Integer.MAX_VALUE);
        distances[src] = 0;
        steps[src] = 0;
        boolean[] visited = new boolean[n];
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> p1.cost - p2.cost);
        heap.offer(new Pair(src, 0));
        while (!heap.isEmpty()) {
            Pair pair = heap.poll();
            visited[pair.city] = true;
            for (Map.Entry<Integer, Integer> neighbor : adjacency.get(pair.city).entrySet()) {
                int distance = distances[pair.city] + neighbor.getValue();
                if (distance < distances[neighbor.getKey()]) {
                    distances[neighbor.getKey()] = distance;
                    steps[neighbor.getKey()] = steps[pair.city] + 1;
                    heap.offer(new Pair(neighbor.getKey(), neighbor.getValue()));
                }
            }
        }
        return steps[dst] <= k ? distances[dst] : -1;
    }


    private static class Pair {
        int city;
        int cost;
        Pair(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}
