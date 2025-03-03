package leetcode.string.stringcompression;


/**
 * Leetcode: https://leetcode.com/problems/string-compression-iii
 */
public class StringCompression3 {

    public static void tests() {
        System.out.println(compressedString( "abcde"));
        System.out.println(compressedString( "abcde").equals("1a1b1c1d1e"));
        System.out.println(compressedString( "aaaaaaaaaaaaaabb"));
        System.out.println(compressedString( "aaaaaaaaay"));
        System.out.println(compressedString( "aaaaaaaaay").equals("9a1y"));
        System.out.println(compressedString("aaaaaaaaaaaaaabb").equals("9a5a2b"));
        System.out.println(compressedString( "yyyyyyyyfffccccqqwwffffffffrrrrrrrrraaaaayyyyyyyyy"));
    }

    public static String compressedString(String word) {
        if (word.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < word.length()) {
            char previous = word.charAt(i);
            int count = 0;
            while (i < word.length() && count < 9 && previous == word.charAt(i)) {
                i++;
                count++;
            }
            sb.append(count).append(previous);
        }
        return sb.toString();
    }

    public static String complicated(String word) {
        if (word.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int count = 0;
        char c = '*';
        while (i < word.length()) {
            if (c == word.charAt(i)) {
                count++;
                if (count == 9) {
                    sb.append(count).append(c);
                    count = 0;
                }
            } else {
                if (c != '*' && count != 0 ) {
                    sb.append(count).append(c);
                }
                count = 1;
                c = word.charAt(i);
            }
            i++;
        }
        if (c != '*' && count != 0) {
            sb.append(count).append(c);
        }
        return sb.toString();
    }
}
