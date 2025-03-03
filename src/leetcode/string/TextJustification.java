package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {


    public static void tests() {
        TextJustification t = new TextJustification();
        print(t.fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16));
        print(t.fullJustify(new String[]{"What","must","be","acknowledgment","shall","be"}, 16));
    }

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> text = new ArrayList<>();
        List<String> currentLine = new ArrayList<>();
        int currentLength = 0;

        int i = 0;
        while (i < words.length) {
            String word = words[i];
            if (currentLength + currentLine.size() + word.length() > maxWidth) {
                // amount of chars in words + 1 space per word (words - 1) + current word exceed the width
                // cut line
                if (currentLine.size() == 1) {
                    // single word
                    String singleWord = currentLine.get(0);
                    StringBuilder sb = new StringBuilder();
                    sb.append(singleWord);
                    for (int j = 0; j < maxWidth - singleWord.length(); j++) {
                        sb.append(" ");
                    }
                    text.add(sb.toString());
                } else {
                    int totalSpaces = maxWidth - currentLength;
                    int spaces = totalSpaces / (currentLine.size() - 1);
                    int remainder = totalSpaces % (currentLine.size() - 1);
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < currentLine.size(); j++) {
                        sb.append(currentLine.get(j));
                        if (j != currentLine.size() - 1) {
                            // add spaces
                            for (int c = 0; c < spaces; c++) {
                                sb.append(' ');
                            }
                        }
                        if (remainder > 0) {
                            sb.append(' ');
                            remainder--;
                        }
                    }
                    text.add(sb.toString());
                }

                currentLine = new ArrayList<>();
                currentLength = 0;
            }
            // word fits in current line, add it
            currentLine.add(word);
            currentLength += word.length();
            i++;
        }
        // complete the last line. It must be left justified.
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < currentLine.size(); i++) {
            sb.append(currentLine.get(i));
            if (i != currentLine.size() - 1) {
                sb.append(' ');
            }
        }
        int remainingSpaces = maxWidth - sb.toString().length();
        for (i = 0; i < remainingSpaces; i++) {
            sb.append(' ');
        }
        text.add(sb.toString());

        return text;

    }

    private static void print(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }
}
