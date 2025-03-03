package leetcode.matrix;

import java.util.HashMap;
import java.util.Map;

public class CompletelyPaintedRowCol {


    public static void tests() {
        System.out.println(firstCompleteIndex(new int[]{1,4,5,2,6,3}, new int[][]{{4,3,5}, {1,2,6}}));
    }


    public static int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, int[]> indexedNumbers = new HashMap<>();
        int[] rows = new int[mat.length];
        int[] cols = new int[mat[0].length];
        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                int num = mat[row][col];
                indexedNumbers.put(num, new int[]{row, col});

                cols[col] = mat.length;
            }
            rows[row] = mat[0].length;
        }
        for (int i = 0; i < arr.length; i++) {
            int[] index = indexedNumbers.get(arr[i]);
            int row = index[0];
            int col = index[1];
            rows[row]--;
            cols[col]--;
            if (rows[row] == 0 || cols[col] == 0) {
                return i;
            }
        }
        // this shouldn't happen
        return -1;
    }
}
