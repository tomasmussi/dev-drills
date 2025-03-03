package algorithms.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeapSort {

    public static void tests() {
        int[] a = new int[]{6, 2, 1, 77, 8, 43, 9, 45, 8, 100, 87, 64};
        heapsort(a);
        System.out.println(Arrays.toString(a));
    }

    private static void heapsort(int[] a) {
        Queue<Integer> heap = new PriorityQueue<>((i1, i2) -> i1 - i2);
        // O(N)
        for (int i = 0; i < a.length; i++) {
            heap.add(a[i]); // O(log(N)) operation
        }
        int i = 0;
        // O(N)
        while (!heap.isEmpty()) {
            a[i++] = heap.poll();
        }
    }


}
