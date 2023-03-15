import java.util.Arrays;

public class k278 {
    public static void main(String[] args) {
        int[] number = {2, 5, 7, 23, 63, 63, 93, 93};
        int max = number[0];
        int max2 = number[0];

        for (int i = 0; i < number.length; i++) {
            if (number[i] > max) {
                max = number[i];
            }
        }
        for (int i = 0; i < number.length; i++) {
            if (number[i] > max2 & number[i] < max) {
                max2 = number[i];
            }
        }
        System.out.println(max2);
    }
}