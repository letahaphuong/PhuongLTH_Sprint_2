import java.util.Scanner;

public class alo1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số bất kỳ");
        int num = scanner.nextInt();

        int[] arr = {1, 2, 3, 4, 5, 6};
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] + arr[j] == num) {
                    System.out.println(arr[i] + "" + arr[j]);
                    break;
                }
            }
        }

    }
}
