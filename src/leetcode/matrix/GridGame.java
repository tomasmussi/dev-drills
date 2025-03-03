package leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class GridGame {
    private final Integer DOWN = 0;
    private final Integer RIGHT = 1;

    public static void tests() {
        GridGame game = new GridGame();
        System.out.println(game.gridGame(new int[][] {{2,5,4}, {1,5,1}} ));
        System.out.println(game.gridGame(new int[][] {{3,3,1}, {8,5,2}} ));
        System.out.println(game.gridGame(new int[][] {{20,3,20,17,2,12,15,17,4,15},{20,10,13,14,15,5,2,3,14,3}} ));
    }


    /**
     * This solution doesn't work.
     * Notice that the problem is not the same as finding the highest number of points the first robot can collect.
     * For example, if grid = [[2, 4, 6], [8, 9, 10]], the first robot could take the path 2 -> 8 -> 9 -> 10 to maximize its points,
     * leaving 4 and 6 for the second robot. But the better strategy is for the first robot to turn down at index 1,
     * leaving either 6 or 8 for the second robot, which would then get max(6, 8) = 8 points instead of 10.
     */
    public long gridGame(int[][] grid) {
        long points = 0;
        solve(grid, true);
        List<Integer> steps = solve(grid, false);
        int row = 0;
        int col = 0;
        for (Integer step : steps) {
            if (step == DOWN) {
                row++;
            } else {
                col++;
            }
            points += grid[row][col];
        }

        return points;
    }

    private List<Integer> solve(int[][] grid, boolean firstRobot) {
        long[][] solution = new long[grid.length][grid[0].length];
        List<Integer>[][] steps = new List[grid.length][grid[0].length];
        long accumulated = 0;
        List<Integer> previous = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            accumulated += grid[row][0];
            solution[row][0] = accumulated;

            steps[row][0] = new ArrayList<>(previous);
            previous.add(DOWN);
        }
        accumulated = 0;
        previous = new ArrayList<>();
        for (int col = 0; col < grid[0].length; col++) {
            accumulated += grid[0][col];
            solution[0][col] = accumulated;

            steps[0][col] = new ArrayList<>(previous);
            previous.add(RIGHT);
        }
        previous = new ArrayList<>();

        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                // to reach (row, col) we either come from up or left
                if (solution[row - 1][col] < solution[row][col - 1]) {
                    // we come from left, means we need to go RIGHT
                    solution[row][col] = solution[row][col - 1] + grid[row][col];
                    List<Integer> currentSteps = new ArrayList<>(steps[row][col - 1]);
                    currentSteps.add(RIGHT);
                    steps[row][col] = currentSteps;
                } else {
                    // we come from up, means we need to go DOWN
                    solution[row][col] = solution[row - 1][col] + grid[row][col];
                    List<Integer> currentSteps = new ArrayList<>(steps[row - 1][col]);
                    currentSteps.add(DOWN);
                    steps[row][col] = currentSteps;
                }
            }
        }
        List<Integer> finalSteps = steps[grid.length - 1][grid[0].length - 1];
        if (firstRobot) {
            grid[0][0] = 0;
            int row = 0;
            int col = 0;
            for (Integer step : finalSteps) {
                if (step == DOWN) {
                    row++;
                } else {
                    col++;
                }
                grid[row][col] = 0;
            }
        }
        return finalSteps;
    }
}
