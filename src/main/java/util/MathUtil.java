package util;

import java.math.BigInteger;

public class MathUtil {
    public static long lcm(long number1, long number2) {
        if (number1 == 0 || number2 == 0)
            return 0;
        else {
            var gcd = gcd(number1, number2);
            return Math.abs(number1 * number2) / gcd;
        }
    }

    public static long gcd(long number1, long number2) {
        if (number1 == 0 || number2 == 0) {
            return number1 + number2;
        } else {
            var absNumber1 = Math.abs(number1);
            long absNumber2 = Math.abs(number2);
            long biggerValue = Math.max(absNumber1, absNumber2);
            long smallerValue = Math.min(absNumber1, absNumber2);

            return gcd(biggerValue % smallerValue, smallerValue);
        }
    }

    public static long getPascalCoefficient(int n, int i) {
        return factorial(n).divide(factorial(i).multiply(factorial(n - i))).longValue();
    }

    public static BigInteger factorial(int k) {
        if (k == 0 || k == 1) {
            return BigInteger.ONE;
        }

        var sum = BigInteger.ONE;
        for (int i = 1; i <= k; i++) {
            sum = sum.multiply(BigInteger.valueOf(i));
        }

        return sum;
    }
}
