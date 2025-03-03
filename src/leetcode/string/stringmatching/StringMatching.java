package leetcode.string.stringmatching;

import datastructures.TrieOld;

import java.util.ArrayList;
import java.util.List;

import static datastructures.TrieOld.WILDCARD_CHAR;

public class StringMatching {

    public static void tests() {
        System.out.println(stringMatching(new String[]{"mass","as","hero","superhero"}));
    }

    public static List<String> stringMatching(String[] words) {
        List<String> matches = new ArrayList<>();
        if (words.length <= 1) {
            return matches;
        }
        TrieOld root = new TrieOld(WILDCARD_CHAR);
        for (String word : words) {
            root.addWord(word);
        }
        for (String word : words) {
            if (root.containsSubstring(word)) {
                matches.add(word);
            }
        }
        return matches;
    }
}
