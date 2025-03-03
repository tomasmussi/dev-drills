package leetcode.minimumarrayend;

public class MinimumArrayEnd {
    public static void tests() {
        System.out.println(minEnd(1,1) + " == 1");
        System.out.println(minEnd(2,1) + " == 2");
        System.out.println(minEnd(3,1) + " == 3");
        System.out.println(minEnd(4,1) + " == 4");

        System.out.println(minEnd(3,4) + " == 6");
        System.out.println(minEnd(2,4) + " == 5");
        System.out.println(minEnd(2,7) + " == 15");
        System.out.println(minEnd(5,4) + " == 15");
    }

    public static long minEnd(int n, int x) {
        long result = x;
        for (int i = 1; i < n; i++) {
            result = (result + 1) | x;
        }
        return result;
    }
}
