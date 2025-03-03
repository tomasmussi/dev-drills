package leetcode.array;

public class MaximumSubarraySum {

    public static void tests() {
        MaximumSubarraySum ms = new MaximumSubarraySum();
        System.out.println(ms.maxAscendingSum(new int[]{10,20,30,5,10,50}));
        System.out.println(ms.maxAscendingSum(new int[]{3}));
        System.out.println(ms.maxAscendingSum(new int[]{10,20,30,40,50}));
        System.out.println(ms.maxAscendingSum(new int[]{3,6,10,1,8,9,9,8,9}) + " == " + 19);
    }

    /**
     * Given an array of positive integers nums, return the maximum possible sum of an ascending subarray in nums.
     *
     * A subarray is defined as a contiguous sequence of numbers in an array.
     *
     * A subarray [numsl, numsl+1, ..., numsr-1, numsr] is ascending if for all i where l <= i < r, numsi  < numsi+1. Note that a subarray of size 1 is ascending.
     * @param nums
     * @return
     */
    public int maxAscendingSum(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxSum = nums[0];
        int currentSum = nums[0];
        int i = 1;
        while (i < nums.length) {
            if (nums[i] > nums[i - 1]) {
                // accumulate the current streak
                currentSum += nums[i];
            } else {
                // cut the streak
                currentSum = nums[i];
            }
            maxSum = Math.max(maxSum, currentSum);
            i++;
        }
        return maxSum;
    }
}
