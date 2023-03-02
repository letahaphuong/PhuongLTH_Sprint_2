import java.util.Arrays;

public class k276 {
    public static void main(String[] args) {
        int[] num = {1, 3, 2, 5, 4};
        Arrays.sort(num);
        int tol = 0;
        int temp = 0;
        int temp2 = 0;
        System.out.println(Arrays.toString(num));
        for (int i = 0; i < num.length; i++) {
            tol += num[i];
            temp = tol - num[0];
            temp2 = tol - num.length - 1;
        }
        System.out.println("Tổng 4 số lớn nhất là:" + temp2);

        System.out.println("Tổng 4 số lớn nhất là:" + temp);

    }
}
