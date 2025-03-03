package leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MinMutation {

    public static void tests() {
        MinMutation m = new MinMutation();
        System.out.println(m.minMutation("AACCGGTT", "AAACGGTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
        System.out.println(m.minMutation("AACCGGTT", "AACCGGTA", new String[]{}));
        System.out.println(m.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"}));
        System.out.println(m.minMutation("AACCGGTT", "AACCGCTA", new String[]{"AACCGGTA","AACCGCTA","AAACGGTA"}));
    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        Map<String, Set<String>> graph = new HashMap<>();
        graph.put(startGene, new HashSet<>());
        graph.put(endGene, new HashSet<>());
        for (String gene1 : bank) {
            for (String gene2 : bank) {
                graph.putIfAbsent(gene1, new HashSet<>());
                graph.putIfAbsent(gene2, new HashSet<>());
                if (distance(gene1, gene2) == 1) {
                    combine(graph, gene1, gene2);
                }
                if (distance(startGene, gene2) == 1) {
                    combine(graph, startGene, gene2);
                }
                if (distance(startGene, gene1) == 1) {
                    combine(graph, startGene, gene1);
                }
            }
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(startGene);
        Set<String> visited = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        visited.add(startGene);
        distances.put(startGene, 0);
        while (!queue.isEmpty()) {
            String gene = queue.poll();
            for (String neighbor : graph.get(gene)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    distances.put(neighbor, distances.get(gene) + 1);
                    queue.add(neighbor);
                }
            }
        }
        return distances.getOrDefault(endGene, -1);
    }

    private void combine(Map<String, Set<String>> graph, String gene1, String gene2) {
        graph.get(gene1).add(gene2);
        graph.get(gene2).add(gene1);
    }

    private int distance(String gene1, String gene2) {
        int distance = 0;
        for (int i = 0; i < gene1.length(); i++) {
            if (gene1.charAt(i) != gene2.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }
}
