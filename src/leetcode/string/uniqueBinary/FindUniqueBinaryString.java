package leetcode.string.uniqueBinary;

import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString {


    public static void tests() {
        var f = new FindUniqueBinaryString();
        System.out.println(f.findDifferentBinaryString(new String[]{"00", "01"}));
    }

    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> usedNumbers = new HashSet<>();
        int n = nums.length;
        for (String num : nums) {
            usedNumbers.add(convertToInt(num));
        }
        for (int i = 0; i <= n; i++) {
            if (!usedNumbers.contains(i)) {
                return convertToString(i, n);
            }
        }
        return "";
    }



    private static int convertToInt(String binaryNum) {
        int result = 0;
        for (int i = binaryNum.length() - 1, pos = 0; i >= 0; i--, pos++) {
            char c = binaryNum.charAt(i);
            if (c == '1') {
                result += (1 << pos);
            }
        }
        return result;

    }

    private static String convertToString(int number, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            if ((number & i) != 0) {
                // bit turned on
                sb.append('1');
            } else {
                // bit turned off
                sb.append('0');
            }
        }
        return sb.toString();
    }
}
