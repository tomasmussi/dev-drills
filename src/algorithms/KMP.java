package algorithms;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    public static void tests() {
        String dnaSequence = "ATCGGATCCTAGCGATTACAGATGCTAGCGATTACAGGATCC";
        String pattern = "GATTACA"; // A known DNA motif or marker

        List<Integer> matches = kmpSearch(dnaSequence, pattern);
        System.out.println("Pattern " + pattern + " found at indices " + matches);

        System.out.println("Pattern ASDF not found " + kmpSearch(dnaSequence, "ASDF"));
    }

    // KMP search: returns starting indices of all matches
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        if (pattern.isEmpty()) return result;

        int[] lps = longestPrefixSuffix(pattern);
        int textIndex = 0, patternIndex = 0;

        while (textIndex < text.length()) {
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                textIndex++;
                patternIndex++;
            }

            if (patternIndex == pattern.length()) {
                result.add(textIndex - patternIndex); // match found
                patternIndex = lps[patternIndex - 1];    // continue searching
            } else if (textIndex < text.length() && text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                if (patternIndex != 0) {
                    patternIndex = lps[patternIndex - 1]; // fallback using LPS
                } else {
                    textIndex++;
                }
            }
        }

        return result;
    }


    public static int[] longestPrefixSuffix(String pattern) {
        int[] lps = new int[pattern.length()];
        int j = 0, i = 1;
        int left = 0, right = 1;
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                // match, same character repeats, record and try next
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    // repeat previous char
                    j = lps[j - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
