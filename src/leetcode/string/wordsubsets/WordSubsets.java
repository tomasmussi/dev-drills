package leetcode.string.wordsubsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSubsets {

    public static void tests() {
        System.out.println(wordSubsets(new String[]{"amazon","apple","facebook","google","leetcode"}, new String[]{"e","o"}));
    }
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> universals = new ArrayList<>();
        Map<Character, Integer> universalFrequencies = new HashMap<>();
        for (String sub : words2) {
            Map<Character, Integer> subFrequency = getFrequencies(sub);
            for (Map.Entry<Character, Integer> entry : subFrequency.entrySet()) {
                Character charac = entry.getKey();
                Integer count = entry.getValue();
                int max = Math.max(universalFrequencies.getOrDefault(charac, 0), count);
                if (max != 0) {
                    universalFrequencies.put(charac, max);
                }
            }
        }
        for (String word : words1) {
            Map<Character, Integer> wordFreq = getFrequencies(word);
            boolean isUniversal = true;
            for (Map.Entry<Character, Integer> entry : universalFrequencies.entrySet()) {
                if (!wordFreq.containsKey(entry.getKey()) || wordFreq.get(entry.getKey()) < entry.getValue()) {
                    isUniversal = false;
                    break;
                }
            }
            if (isUniversal) {
                universals.add(word);
            }
        }
        return universals;
    }

    private static Map<Character, Integer> getFrequencies(String word) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : word.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }
        return frequencies;
    }
}
