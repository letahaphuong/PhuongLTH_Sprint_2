package on_tap.on_tap_alg;

import java.util.Scanner;

public class alg1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số cần tính tổng: ");
        int number = scanner.nextInt();
        int count =0;
        int countSNT =2;
        int sum =0;
        while (count < number){
            int temp = 0;
            for (int i = 1; i <= countSNT ; i++) {
                if (countSNT % i == 0) temp++;
            }
            if (temp == 2){
                sum += countSNT;
                count++;
            }
            countSNT++;
        }
        System.out.println(sum);
    }
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập số cần kiểm tra: ");
//        int input = scanner.nextInt();
//
//        System.out.println(checkSNT(input));
//
//    }

//    public static boolean checkSNT(int input) {
//        boolean check = false;
//        if (input == 1 || input == 2) {
//            return check = true;
//        }
//        int temp = 0;
//        for (int i = 1; i <= input; i++) {
//
//            if (input % i == 0) {
//                temp++;
//            }
//        }
//        if (temp == 2) {
//            return check = true;
//        }
//        check = false;
//
//        return check;
//    }

}


