package russiantrade.util;

public class DigitCount {
    public static int count(int n) {
        int temp = 1;
        int count = 0;

        while(temp <= n) {
            temp *= 10;
            count++;
        }
        return count;
    }
}
