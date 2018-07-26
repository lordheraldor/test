import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCodeTestClass {

    public static void main(String... args) {
        int x = 321;

        System.out.println(reverse(x));

    }

    public static int reverse(int x) {
        long reversedNum = 0;
        long input_long = x;

        while (input_long != 0) {
            reversedNum = reversedNum * 10 + input_long % 10;
            input_long = input_long / 10;
        }

        if (reversedNum > Integer.MAX_VALUE || reversedNum < Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return (int) reversedNum;
    }
}
