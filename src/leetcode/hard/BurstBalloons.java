package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class BurstBalloons {

    public static void tests() {
        var t = new BurstBalloons();
        System.out.println(t.maxCoins(new int[]{3, 1, 5, 8}));
    }

    /**
     The logic here is to think what happens when the ith balloon is exploded the last, not the first
     */
    public int maxCoins(int[] nums) {
        int[] expanded = new int[nums.length + 2];
        for (int e = 1, i = 0; i < nums.length; i++, e++) {
            expanded[e] = nums[i];
        }
        expanded[0] = 1;
        expanded[expanded.length - 1] = 1;
        Map<Tuple, Integer> memo = new HashMap<>();
        return maxCoins(memo, expanded, 1, nums.length);
    }

    private int maxCoins(Map<Tuple, Integer> memo, int[] nums, int left, int right) {
        if (left > right) {
            // base case
            return 0;
        }
        Tuple t = new Tuple(left, right);
        if (memo.containsKey(t)) {
            System.out.println("memo! " + t);
            return memo.get(t);
        }
        // initial case
        memo.put(t, 0);
        for (int i = left; i <= right; i++) {
            int leftSubproblem = maxCoins(memo, nums, left, i - 1);

            int rightSubproblem = maxCoins(memo, nums, i + 1, right);

            int coinsByLastIthBalloon = nums[left - 1] * nums[i] * nums[right + 1];

            int total = coinsByLastIthBalloon + leftSubproblem + rightSubproblem;

            memo.put(t, Math.max(total, memo.get(t)));
        }
        return memo.get(t);
    }

    private record Tuple(int left, int right) {
    }
}
