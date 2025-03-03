package leetcode.array;

public class LongestIncreasingOrDecreasing {



    public static void tests() {
        LongestIncreasingOrDecreasing l = new LongestIncreasingOrDecreasing();
        System.out.println(l.longestMonotonicSubarray(new int[]{3,2,1}));
    }

    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int longestIncreasing = 0, currentIncreasing = 1;
        int longestDecreasing = 0, currentDecreasing = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                currentIncreasing++;
                longestDecreasing = Math.max(longestDecreasing, currentDecreasing);
                currentDecreasing = 1;
            } else if (nums[i] > nums[i + 1]) {
                currentDecreasing++;
                longestIncreasing = Math.max(longestIncreasing, currentIncreasing);
                currentIncreasing = 1;
            } else {
                longestDecreasing = Math.max(longestDecreasing, currentDecreasing);
                longestIncreasing = Math.max(longestIncreasing, currentIncreasing);
                currentIncreasing = 1;
                currentDecreasing = 1;
            }
        }
        longestIncreasing = Math.max(longestIncreasing, currentIncreasing);
        longestDecreasing = Math.max(longestDecreasing, currentDecreasing);
        return Math.max(longestIncreasing, longestDecreasing);
    }
}
