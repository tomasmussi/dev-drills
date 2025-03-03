package leetcode.string.wordbreak;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {

    public static void tests() {
        System.out.println(wordBreakPhrase("iamawesome", List.of("i", "am", "awesome")));
        System.out.println(wordBreakPhrase("leetcode", List.of("leet", "code")));
        System.out.println(wordBreakPhrase("thereforeiamtherelookingtodowhat", List.of("there", "therefore", "i", "am", "looking", "todo", "what")));
        System.out.println(wordBreakPhrase("aaabbb", List.of("aa", "aaab", "aaabb")));
        System.out.println(wordBreakPhrase("missing", List.of("word")));
    }

    public static String wordBreakPhrase(String s, List<String> wordDict) {
        return wordBreakPhrase(s, new HashSet<>(wordDict), new HashMap<>());
    }

    private static String wordBreakPhrase(String s, Set<String> wordDict, Map<String, String> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        if (s.isEmpty()) {
            return "";
        }
        String maxSentence = "";
        for (int i = 0; i < s.length() + 1; i++) {
            String prefix = s.substring(0, i);
            if (wordDict.contains(prefix)) {
                String suffix = s.substring(i);
//                memo.put(prefix, )
                String splitWords = wordBreakPhrase(suffix, wordDict, memo);
                String newSentence = prefix + (splitWords.isEmpty() ? "" : " " + splitWords);
                if (newSentence.length() > maxSentence.length()) {
                    maxSentence = newSentence;
                }
            } else {
                memo.put(prefix, "");
            }
        }
        return maxSentence;
    }

    public static boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, new HashSet<>(wordDict), 0) != -1;
    }

    private static int wordBreak(String s, Set<String> wordDict, int index) {
        if (s.isEmpty()) {
            return 0;
        }
        int maxMatches = -1;
        for (int i = index; i < s.length() + 1; i++) {
            String prefix = s.substring(index, i);
            if (wordDict.contains(prefix)) {
                String suffix = s.substring(i);
                int splitWords = wordBreak(suffix, wordDict, 0);
                if (splitWords != -1) {
                    maxMatches = Math.max(1 + splitWords, maxMatches);
                }
            }
        }
        return maxMatches;
    }
}
