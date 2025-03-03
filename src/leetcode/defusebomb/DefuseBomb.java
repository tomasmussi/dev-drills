package leetcode.defusebomb;

import java.util.Arrays;

public class DefuseBomb {

    public static void tests() {
        System.out.println(Arrays.toString(decrypt(new int[]{5,7,1,4}, 3)) + " == [12,10,16,13]");
        System.out.println(Arrays.toString(decrypt(new int[]{1,2,3,4}, 0)) + " == [0,0,0,0]");
        System.out.println(Arrays.toString(decrypt(new int[]{2,4,9,3}, -2)) + " == [12,5,6,13]");
    }

    public static int[] decrypt(int[] code, int k) {
        int[] solution = new int[code.length];

        return null;
    }

    public static int[] bruteForcedecrypt(int[] code, int k) {
        int[] solution = new int[code.length];
        if (k > 0) {
            for (int i = 0; i < solution.length; i++) {
                int sum = 0;
                for (int j = i + 1; j < i + k + 1; j++) {
                    sum += code[j % solution.length];
                }
                solution[i] = sum;
            }
        } else if (k < 0) {
            int absK = Math.abs(k);
            for (int i = absK; i < solution.length + absK; i++) {
                int sum = 0;
                for (int j = i - 1; j > i - absK - 1; j--) {
                    sum += code[j % solution.length];
                }
                solution[i % solution.length] = sum;
            }
        }
        return solution;
    }
}
