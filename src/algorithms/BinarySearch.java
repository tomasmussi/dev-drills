package algorithms;

public class BinarySearch {

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[]{1,2,7,82}, 7));
        System.out.println(binarySearch(new int[]{1,2,7,82}, 100));
        System.out.println(binarySearch(new int[]{1,2,7,82}, 50));
        System.out.println(binarySearch(new int[]{1,2, 3,7,82}, 3));
        System.out.println(binarySearch(new int[]{1,2, 3,7,82}, 1));

        System.out.println((int) Math.ceil(Math.log10(16) / Math.log10(2)));
    }

    public static final int binarySearch(int[] array, int element) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int middle = ((right - left) / 2) + left;
            int middleElement = array[middle];
            if (middleElement == element) {
                return middle;
            } else if (middleElement < element) {
                // lookup right
                left = middle + 1;
            } else {
                // lookup left
                right = middle - 1;
            }
        }
        return -1;
    }

    public static final int binarySearchRecursive(int[] array, int element) {
        return binarySearchRecursive(array, element, 0, array.length - 1);
    }

    public static final int binarySearchRecursive(int[] array, int element, int left, int right) {
        if (left <= right) {
            int middle = ((right - left) / 2) + left;
            int middleElement = array[middle];
            if (middleElement == element) {
                return middle;
            } else if (middleElement < element) {
                // lookup right
                return binarySearchRecursive(array, element, middle + 1, right);
            } else {
                return binarySearchRecursive(array, element, left, middle - 1);
            }
        }
        return -1;
    }
}
