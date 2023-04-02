package on_tap.on_tap_alg;

import java.util.Scanner;

public class alg4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your number: ");
        int number = scanner.nextInt();
        String result="";
        while (number > 0){
            result = number % 8 + result;
            number = number / 8;
        }
        System.out.println(result);
    }
}
