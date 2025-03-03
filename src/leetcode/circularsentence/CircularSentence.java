package leetcode.circularsentence;

/**
 * Leetcode: https://leetcode.com/problems/circular-sentence
 */
public class CircularSentence {

    public static void tests() {
        System.out.println(isCircularSentence("leetcode exercises sound delightful"));
        System.out.println(isCircularSentence("happy Leetcode"));
        System.out.println(isCircularSentence("Leetcode eisc cool"));
    }

    public static boolean isCircularSentence(String sentence) {
        String[] array = sentence.split(" ");
        if (array.length == 1) {
            String word = array[0];
            return word.charAt(0) == word.charAt(word.length() - 1);
        } else {
            // special case: first vs last word
            char firstChar = array[0].charAt(0);
            String lastWord = array[array.length - 1];
            char lastChar = lastWord.charAt(lastWord.length() - 1);
            if (firstChar != lastChar) {
                return false;
            }
        }
        for (int left = 0, right = 1; right < array.length; left++, right++) {
            char leftChar = array[left].charAt(array[left].length() - 1);
            char rightChar = array[right].charAt(0);
            if (leftChar != rightChar) {
                return false;
            }
        }

        return true;
    }
}
