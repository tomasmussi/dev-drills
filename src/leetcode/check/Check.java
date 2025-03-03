package leetcode.check;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Check {

    public static void tests() {
        System.out.println(checkIfExist(new int[] {10,2,5,3}));
        System.out.println(checkIfExist(new int[] {3,1,7,11}));
        System.out.println(checkIfExist(new int[] {1,1,1,1}));
        System.out.println(checkIfExist(new int[] {-2,0,10,-19,4,6,-8}));
        System.out.println(checkIfExist(new int[] {1,0,1,0}));
        System.out.println(checkIfExist(new int[] {1,0,2}));
    }

    public static boolean checkIfExist(int[] arr) {
        Map<Integer, Set<Integer>> nums = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            nums.computeIfAbsent(num, k -> new HashSet<>());
            nums.get(num).add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            int doubleNum = arr[i] * 2;
            if (nums.containsKey(doubleNum)) {
                if (doubleNum == 0) {
                    // handle special
                    Set<Integer> indices = nums.get(doubleNum);
                    if (indices.size() > 1) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkIfExistOld(int[] arr) {
        Map<Integer, Set<Integer>> doubles = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i] * 2;
            doubles.computeIfAbsent(num, k -> new HashSet<>());
            doubles.get(num).add(i);
        }
        for (int i = 0; i < arr.length; i++) {
            int doubleNum = arr[i];
            if (doubles.containsKey(doubleNum)) {
//                Set<Integer> indices = doubles.get(doubleNum);
//                if (!indices.contains(i) && indices.size() > 1) {
                    return true;
//                }
            }
        }
        return false;
    }

    // inefficient
    public static boolean checkIfExistInefficient(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] * 2 == arr[j] || arr[j] * 2 == arr[i]) {
                    return true;
                }
            }
        }
        return false;
    }
}
