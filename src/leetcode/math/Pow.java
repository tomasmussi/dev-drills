package leetcode.math;

public class Pow {

    public static void tests() {
        var pow = new Pow();
        System.out.println(pow.myPow(2, 10));
        System.out.println(pow.myPow(2, -2));
        System.out.println(pow.myPow(2, 0));
    }

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        double result = myPowRec(x, Math.abs(n));
        return n > 0 ? result : 1 / result;
    }

    private double myPowRec(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double result = myPowRec(x, n / 2);
        result = result * result;

        if (n % 2 == 1) {
            result *= x;
        }
        return result;
    }

    public double myPowLinear(double x, int n) {
        if (n == 0) {
            return 1;
        }
        int i = 1;
        double result = x;
        while (i < Math.abs(n)) {
            result = result * x;
            i++;
        }
        return n > 0 ? result : 1 / result;
    }
}
