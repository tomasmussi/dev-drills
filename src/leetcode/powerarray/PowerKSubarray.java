package leetcode.powerarray;

import java.util.Arrays;

public class PowerKSubarray {

    public static void tests() {
        System.out.println(Arrays.toString(resultsArray(new int[]{1,2,3,4,3,2,5}, 3))
                + " == " + Arrays.toString(new int[]{3,4,-1,-1,-1}));


        System.out.println(Arrays.toString(resultsArray(new int[]{3,2,3,2,3,2}, 2))
                + " == " + Arrays.toString(new int[]{-1,3,-1,3,-1}));

        System.out.println(Arrays.toString(resultsArray(new int[]{1}, 1))
                + " == " + Arrays.toString(new int[]{1}));
    }

    /**
     * Consecutive: nums[i] == nums[i + 1] + 1
     *
     * let's see I can do that keeping the
     * 1,2,3   ,4 ,3,2,5    k = 3
     * 1,2,3 -> yes
     * 2,3, -> 4 consecutive = true
     * 2,3, -> 7 consecutive = false
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] resultsArray(int[] nums, int k) {
        if (nums.length == 1 || k == 1) {
            return nums;
        }
        int consecutive = 0;
        int[] solution = new int[nums.length - k + 1];
        int i = 0;
        while (i < k - 1) {
            if (nums[i] + 1 == nums[i + 1]) {
                consecutive++;
            } else {
                consecutive = 0;
            }
            i++;
        }
        int iSol = 0;
        for (; i < nums.length; i++, iSol++) {
            if (nums[i - 1] + 1 == nums[i]) {
                consecutive++;
            } else {
                consecutive = 1;
            }
            if (consecutive < k) {
                solution[iSol] = -1;
            } else {
                solution[iSol] = nums[i];
            }

        }
        return solution;
    }
}
