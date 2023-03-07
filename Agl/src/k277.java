import java.util.Scanner;

public class k277 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhập số");
        int num = scanner.nextInt();

        int rev = 0;
        while (num != 0){
            int temp = num % 10;
            rev = rev * 10 + temp;
            num = num /10;
        }
        System.out.println(rev);
    }
}
