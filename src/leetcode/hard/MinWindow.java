package leetcode.hard;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class MinWindow {

    public static void tests() throws IOException {
        var m = new MinWindow();
        System.out.println(m.minWindow("ab", "b"));
        System.out.println(m.minWindow("cabwefgewcwaefgcf", "cae"));
        byte[] encoded = Files.readAllBytes(Paths.get("src/resources/minWindowS.txt"));
        var s = new String(encoded, StandardCharsets.UTF_8);
        encoded = Files.readAllBytes(Paths.get("src/resources/minWindowT.txt"));
        var t = new String(encoded, StandardCharsets.UTF_8);
        System.out.println(m.minWindow(s, t).length());
    }

    public String minWindow(String s, String t) {
        if (t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> tChars = new HashMap<>();
        for (char c : t.toCharArray()) {
            tChars.put(c, tChars.getOrDefault(c, 0) + 1);
        }
        int neededChars = tChars.size();
        int currentChars = 0;
        int left = 0;
        int right = 0;
        char[] sArr = s.toCharArray();
        String minString = null;
        while (right < sArr.length) {
            // increase window size if chars are not met
            while (right < sArr.length && currentChars < neededChars) {
                char c = sArr[right];
                if (tChars.containsKey(c)) {
                    window.put(c, window.getOrDefault(c, 0) + 1);
                    if (window.get(c).equals(tChars.get(c))) {
                        currentChars++;
                    }
                }
                right++;
            }
            // try decreasing the window size
            while (currentChars == neededChars) {
                // check if there is a solution
                if (minString == null || (right - left) < s.length()) {
                    minString = s.substring(left, right);
                }
                // decrease window
                char c = sArr[left];
                if (tChars.containsKey(c)) {
                    window.put(c, window.get(c) - 1);
                    if (window.get(c) < tChars.get(c)) {
                        currentChars--;
                    }
                }
                left++;
            }
        }
        return minString != null ? minString : "";
    }
}
