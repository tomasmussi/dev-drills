package datastructures;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.isEndOfWord = true;
    }

    public boolean search(String word) {
        return traverse(word, true);
    }

    public boolean startsWith(String prefix) {
        return traverse(prefix, false);
    }

    private boolean traverse(String word, boolean isWholeWord) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.children.get(c) == null) {
                return false;
            }
            current = current.children.get(c);
        }
        if (isWholeWord) {
            return current.isEndOfWord;
        } else {
            return true;
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isEndOfWord = false;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.addWord("apple");
        trie.addWord("app");
        trie.addWord("application");

        System.out.println(trie.search("application"));
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("aptitude"));

        System.out.println(trie.startsWith("ap"));
        System.out.println(trie.startsWith("apple"));
    }
}
