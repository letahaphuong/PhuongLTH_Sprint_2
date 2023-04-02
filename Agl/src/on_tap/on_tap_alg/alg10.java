package on_tap.on_tap_alg;

import java.util.Scanner;

public class alg10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số cần đảo ngược:");
        int number = scanner.nextInt();
        int r = 0;
        while (number > 0) {
            int temp = number % 10;
            r = r * 10 + temp;
            number = number / 10;
        }
        System.out.println(r);
    }

//    public static void main(String[] args) {
//        int n = 10;
//        String result = "";
//        while (n > 0) {
//            result = n % 8 + result;
//            n = n / 8;
//        }
//        System.out.println(result);
//    }
}
