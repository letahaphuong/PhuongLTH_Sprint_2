package on_tap.on_tap_alg;

import java.util.Arrays;

public class alg11 {
    public static void main(String[] args) {
        int[] num = {1, 5, 3, 4, 2};
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] < num[j]) {
                    int temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
            sum += num[i];
        }
        System.out.println("Tổng 4 số lớn nhât" + (sum - num[num.length - 1]));
        System.out.println("Tổng 4 số bé nhất" + (sum - num[0]));

    }
}