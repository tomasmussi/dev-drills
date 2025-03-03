package datastructures;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TrieOld {

    public static final char WILDCARD_CHAR = '*';

    private final char current;

    private final Map<Character, TrieOld> children = new HashMap<>();

    public TrieOld() {
        this.current = WILDCARD_CHAR;
    }

    public TrieOld(char current) {
        this.current = current;
    }

    public void addChild(char next) {
        children.put(next, new TrieOld(next));
    }

    public void addChild(TrieOld child) {
        children.put(child.current, child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieOld trie = (TrieOld) o;
        return current == trie.current;
    }

    @Override
    public int hashCode() {
        return Objects.hash(current);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (children.isEmpty()) {
            sb.append(current).append("\n");
        }
        for (TrieOld c: children.values()) {
            sb.append(current).append(" -> ").append(c.toString());
        }
        return sb.toString();
    }

    public void addWord(String word) {
        Map<Character, TrieOld> next = children;
        for (char c : word.toCharArray()) {
            next.computeIfAbsent(c, TrieOld::new);
            next = next.get(c).children;
        }
    }

    public boolean hasPrefix(String prefix) {
        return hasPrefix(children, prefix.toCharArray(), 0);
    }

    public boolean containsSubstring(String substring) {
        boolean containsSubstring = false;
        char[] arr = substring.toCharArray();
        for (TrieOld child : children.values()) {
            containsSubstring |= containsSubstring(child, arr,0);
        }
        return containsSubstring;
    }

    private boolean containsSubstring(TrieOld child, char[] charArray, int index) {
        if (charArray.length == index) {
            return true;
        }
        char currentPrefix = charArray[index];
        if (child.current != currentPrefix) {
            return false;
        }
        boolean isSubstring = false;
        for (TrieOld trie : child.children.values()) {
            isSubstring |= containsSubstring(trie, charArray, index + 1);
        }
        return isSubstring;
    }

    public static boolean hasPrefix(Map<Character, TrieOld> next, char[] prefix, int index) {
        if (prefix.length == index) {
            return !next.isEmpty();
        }
        char currentPrefix = prefix[index];
        if (currentPrefix == WILDCARD_CHAR) {
            boolean prefixExists = false;
            for (TrieOld child : next.values()) {
                prefixExists |= hasPrefix(child.children, prefix, index + 1);
            }
            return prefixExists;
        }
        return next.containsKey(currentPrefix)
                && hasPrefix(next.get(currentPrefix).children, prefix, index + 1);
    }

    public static TrieOld buildFromWord(String word) {
        TrieOld root = null;
        TrieOld previous = null;
        for (char c : word.toCharArray()) {
            if (root == null) {
                root = new TrieOld(c);
                previous = root;
            } else {
                TrieOld next = new TrieOld(c);
                previous.addChild(next);
                previous = next;
            }
        }
        return root;
    }

    public static TrieOld buildFromDictionary(Collection<String> words) {
        TrieOld root = new TrieOld(WILDCARD_CHAR);
        for (String word : words) {
            root.addWord(word);
        }
        return root;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"mass","as","hero","superhero"};

        TrieOld trie = buildFromDictionary(List.of(words));
        System.out.println(trie);
        System.out.println(trie.hasPrefix("mass") + " == false ");
        System.out.println(trie.hasPrefix("mas") + " == true ");
        System.out.println(trie.hasPrefix("her") + " == true ");
        System.out.println(trie.hasPrefix("sup*rher") + " == true ");
        System.out.println(trie.hasPrefix("sup*rho") + " == false ");
//        for (String word : words) {
//            if (trie.containsPrefix(word)) {
//                System.out.println(word);
//            }
//        }
    }

}
