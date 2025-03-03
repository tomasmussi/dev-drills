package algorithms.sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void tests() {
        int[] a = new int[]{6, 2, 1, 77, 8, 43, 9, 45, 8, 100, 87, 64};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }
    private static void quickSort(int[] ints) {
        quickSort(ints, 0, ints.length - 1);
    }

    private static void quickSort(int[] ints, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex  = partitionRandom(ints, left, right);
        quickSort(ints, left, pivotIndex - 1);
        quickSort(ints, pivotIndex + 1, right);
    }

    private static int partitionRandom(int[] ints, int left, int right) {
        int pivotIndex = new Random().nextInt(right - left) + left;
        int pivot = ints[pivotIndex];

        // move to last
        swap(ints, pivotIndex, right);
        pivotIndex = left;
        for (int i = left; i < right; i++) {
            if (ints[i] < pivot) {
                // swap
                swap(ints, pivotIndex, i);
                pivotIndex++;
            }
        }
        // move to its final place
        swap(ints, pivotIndex, right);
        return pivotIndex;
    }

    public static int partitionChatGPT(int[] arr, int low, int high) {
        Random random = new Random();
        int pivotIndex = low + random.nextInt(high - low + 1);
        int pivotValue = arr[pivotIndex];

        // Swap pivot with the last element
        swap(arr, pivotIndex, high);

        int i = low; // Pointer for elements smaller than pivot

        for (int j = low; j < high; j++) {
            if (arr[j] < pivotValue) {
                swap(arr, i, j);
                i++;
            }
        }

        // Move pivot to its final place
        swap(arr, i, high);
        return i; // Return pivot's final index
    }

    /**
     * Pivot is the last element of the array
     */
    private static int partition(int[] ints, int left, int right) {
        int pivot = ints[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (ints[j] <= pivot) {
                i++;
                swap(ints, i, j);
            }
        }
        swap(ints, i + 1, right);
        return i + 1;
    }

    private static void swap(int[] ints, int i, int j) {
        int temp = ints[i];
        ints[i] = ints[j];
        ints[j] = temp;
    }
}
