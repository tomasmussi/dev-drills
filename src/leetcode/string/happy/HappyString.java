package leetcode.string.happy;

import java.util.Set;
import java.util.TreeSet;

public class HappyString {


    public static String getHappyString(int n, int k) {
        Set<String> combinations = new TreeSet<>();
        generateCombinations(combinations, n, "a");
        generateCombinations(combinations, n, "b");
        generateCombinations(combinations, n, "c");
        if (k <= combinations.size()) {
            return combinations.stream().toList().get(k - 1);
        }
        return "";
    }

    private static void generateCombinations(Set<String> combinations, int n, String current) {
        if (current.length() == n) {
            combinations.add(current);
            return;
        }
        char c = current.charAt(current.length() - 1);
        switch (c) {
            case 'a':
                generateCombinations(combinations, n, current + "b");
                generateCombinations(combinations, n, current + "c");
                break;
            case 'b':
                generateCombinations(combinations, n, current + "a");
                generateCombinations(combinations, n, current + "c");
                break;
            case 'c':
                generateCombinations(combinations, n, current + "a");
                generateCombinations(combinations, n, current + "b");
                break;
        }
    }
}
