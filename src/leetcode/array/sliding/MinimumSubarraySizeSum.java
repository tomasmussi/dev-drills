package leetcode.array.sliding;

public class MinimumSubarraySizeSum {

    public static void tests() {
        System.out.println(minSubArrayLen(9, new int[]{2,8,1,8}) + " == 2");
        System.out.println(minSubArrayLen(7, new int[]{2,3,1,2,4,3}) + " == 2");
        System.out.println(minSubArrayLen(4, new int[]{1,4,4}) + " == 1");
        System.out.println(minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}) + " == 0");
    }

    public static int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 1) {
            return nums[0] >= target ? 1 : 0;
        }
        int maxSum = 0;
        for (int i : nums) {
            maxSum += i;
            if (i >= target) {
                return 1;
            }
        }
        if (maxSum < target) {
            return 0;
        }
        int left = 0;
        int right = 1;
        int currentSum = nums[left];
        int currentSolution = 1;
        int bestSolution = Integer.MAX_VALUE;
        while (right < nums.length) {
            if (currentSum < target) {
                // not enough elements in sliding window
                currentSum += nums[right];
                currentSolution++;
                right++;
            } else {
                // enough elements, dispose the left side
                if (currentSolution < bestSolution) {
                    bestSolution = currentSolution;
                }
                currentSum -= nums[left];
                currentSolution--;
                left++;
            }
        }
        while (left < nums.length) {
            // enough elements, dispose the left side
            if (currentSum >= target && currentSolution < bestSolution) {
                bestSolution = currentSolution;
            }
            currentSum -= nums[left];
            currentSolution--;
            left++;
        }
        return bestSolution;
    }
}
