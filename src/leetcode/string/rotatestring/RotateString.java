package leetcode.string.rotatestring;

/**
 * Leetcode: https://leetcode.com/problems/rotate-string
 */
public class RotateString {

    public static void tests() {
        System.out.println(rotateString("abcde", "cdeab"));
        System.out.println(rotateString("defdefdefabcabc", "defdefabcabcdef"));
    }

    public static boolean rotateString(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }
        String doubleString = s + s;

        for (int i = 0; i < doubleString.length(); i++) {
            int sIndex = i;
            int goalIndex = 0;
            while (goalIndex < goal.length() && sIndex < doubleString.length() && doubleString.charAt(sIndex) == goal.charAt(goalIndex)) {
                sIndex++;
                goalIndex++;
            }
            if (goalIndex == goal.length() && sIndex < doubleString.length()) {
                return true;
            }
        }
        return false;
    }

    /**
     * What's wrong here is that it doesn't account for repeating patterns.
     * If you start by hitting a pattern that repeats, and find that it is not a solution,
     * this approach doesn't go back to check where the pattern can still apply.
     */
    public static boolean firstAttempt(String s, String goal) {
        if (s.length() != goal.length())
            return false;
        int start = 0;
        while (start < s.length() && s.charAt(start) != goal.charAt(0)) {
            start++;
        }
        if (start == s.length()) {
            return false;
        }
        int goalIndex = 0;
        while (goalIndex < goal.length()) {
            if (s.charAt(start % s.length()) != goal.charAt(goalIndex)) {
                return false;
            }
            start++;
            goalIndex++;
        }
        return true;
    }
}
