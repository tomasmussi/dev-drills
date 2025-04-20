package algorithms;

public class Knapsack {

    public static void tests() {
        var knapSack = new Knapsack();

        System.out.println(knapSack.knapsackRecursive(new int[] {1, 3, 4, 5}, new int[] {1, 4, 5, 7}, 7, 0) + " == 9");
        System.out.println(knapSack.knapsack(new int[] {1, 3, 4, 5}, new int[] {1, 4, 5, 7}, 7) + " == 9");
    }

    private int knapsack(int[] weights, int[] values, int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 0; i <= weights.length; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else {
                    int with = 0;
                    if (weights[i - 1] <= w) {
                        // there's room
                        with = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    }
                    // no room, repeat last
                    int without = dp[i - 1][w];
                    dp[i][w] = Math.max(with, without);
                }
            }
        }
        return dp[weights.length][capacity];
    }

    private int knapsackRecursive(int[] weights, int[] values, int capacity, int i) {
        if (i == weights.length) {
            return 0;
        }
        // either include
        int with = 0;
        if (weights[i] <= capacity) {
            with = knapsackRecursive(weights, values, capacity - weights[i], i + 1) + values[i];
        }

        // or not
        int without = knapsackRecursive(weights, values, capacity, i + 1);
        return Math.max(with, without);
    }

}
