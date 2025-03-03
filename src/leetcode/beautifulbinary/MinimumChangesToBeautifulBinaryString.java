package leetcode.beautifulbinary;

public class MinimumChangesToBeautifulBinaryString {

    public static int minChanges(String s) {
        if (s.length() % 2 != 0) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i+= 2) {
            if (s.charAt(i) != s.charAt(i + 1)) {
                count++;
            }
        }
        return count;
    }
}
