package leetcode.matrix;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

    private static final Integer ROTTEN = 2;
    private static final Integer FRESH = 1;

    public static void tests() {
        System.out.println(orangesRotting(new int[][]{{2,1,1}, {1,1,0}, {0,1,1} }));
        System.out.println(orangesRotting(new int[][]{{2,1,1}, {1,1,1}, {0,1,2} }));
        System.out.println(orangesRotting(new int[][]{{2,1,1}, {0,1,1}, {1,0,1} }));
    }
    public static int orangesRotting(int[][] grid) {
        int freshOranges = 0;
        Queue<int[]> oranges = new LinkedList<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == FRESH) {
                    freshOranges++;
                } else if (grid[row][col] == ROTTEN) {
                    oranges.add(new int[]{row, col});
                }
            }
        }
        if (freshOranges == 0) {
            return 0;
        }
        if (oranges.isEmpty()) {
            return -1;
        }
        int time = -1;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!oranges.isEmpty()) {
            int size = oranges.size();
            for (int i = 0; i < size; i++) {
                int[] coords = oranges.poll();
                int row = coords[0];
                int col = coords[1];
                visited[row][col] = true;
                for (int[] direction : directions) {
                    int r = row + direction[0];
                    int c = col + direction[1];
                    if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == FRESH && !visited[r][c]) {
                        grid[r][c] = ROTTEN;
                        oranges.add(new int[]{r, c});
                        freshOranges--;
                    }
                }
            }
            time++;
        }
        return freshOranges > 0 ? -1 : time;
    }

    private static void fill(Queue<int[]> oranges, boolean[][] visited, int[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == FRESH && !visited[row][col]) {
            oranges.add(new int[]{row, col});
        }
    }

    /*
    public static int orangesRotting(int[][] grid) {
        int minTime = 0;
        int[][] minTimes = new int[grid.length][grid[0].length];
        for (int[] row : minTimes) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == ROTTEN && minTimes[row][col] == Integer.MAX_VALUE) {
                    minTimes[row][col] = 0;
                    rotten(grid, minTimes, row, col + 1, 1);
                    rotten(grid, minTimes, row, col - 1, 1);
                    rotten(grid, minTimes, row + 1, col, 1);
                    rotten(grid, minTimes, row - 1, col, 1);
                }
            }
        }
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == FRESH) {
                    return -1;
                } else if (grid[row][col] == ROTTEN) {
                    minTime = Math.max(minTime, minTimes[row][col]);
                }
            }
        }
        return minTime;
    }

    private static void rotten(int[][] grid, int[][] minTimes, int row, int col, int time) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] == FRESH) {
            grid[row][col] = ROTTEN;
            minTimes[row][col] = Math.min(minTimes[row][col], time);
            rotten(grid, minTimes, row + 1, col, time + 1);
            rotten(grid, minTimes, row - 1, col, time + 1);
            rotten(grid, minTimes, row, col + 1, time + 1);
            rotten(grid, minTimes, row, col - 1, time + 1);
        }
    }

     */
}
