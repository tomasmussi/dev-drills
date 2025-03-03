package algorithms.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void tests() {
        int[] a = new int[]{6, 2, 1, 77, 8, 43, 9, 45, 8, 100, 87, 64};
        int[] solution = mergesort(a);
        System.out.println(Arrays.toString(solution));
    }

    private static int[] mergesort(int[] array) {
        int[] solution = mergesort(array, 0, array.length - 1);
        for (int i = 0; i < solution.length; i++) {
            array[i] = solution[i];
        }
        return solution;
    }

    private static int[] mergesort(int[] array, int left, int right) {
        if (left == right) {
            return new int[]{array[left]};
        }
        int mid = left + ((right - left) / 2);
        int[] leftArray = mergesort(array, left, mid);
        int[] rightArray = mergesort(array, mid + 1, right);
        return merge(leftArray, rightArray);
    }

    private static int[] merge(int[] leftArray, int[] rightArray) {
        int[] result = new int[leftArray.length + rightArray.length];
        int left = 0;
        int right = 0;
        int  i = 0;
        while (left < leftArray.length && right < rightArray.length) {
            if (leftArray[left] <= rightArray[right]) {
                result[i++] = leftArray[left++];
            } else {
                result[i++] = rightArray[right++];
            }
        }
        while (left < leftArray.length) {
            result[i++] = leftArray[left++];
        }
        while (right < rightArray.length) {
            result[i++] = rightArray[right++];
        }
        return result;
    }
}
