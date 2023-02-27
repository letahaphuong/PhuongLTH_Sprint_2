import java.util.*;

public class ThapPhanSangBatPhan {
    //    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập number");
//        int number = scanner.nextInt();
//        String result = "";
//        while (number > 0) {
//            result = number % 8 + result;
//            number = number / 8;
//
//        }
//        System.out.println(result);
//
//    }
    public static void main(String[] args) {
        int count;
        String temp;
        Scanner scan = new Scanner(System.in);
        System.out.print("Nhập vào số chuỗi mà bạn muốn sắp xếp theo Alphabet  :");
        count =  scan.nextInt();
        String str[] = new String[count];
        Scanner scan2 = new Scanner(System.in);
        System.out.println("Nhập giá trị cho từng chuỗi:");
        for (int i = 0; i < count; i++) {
            str[i] = scan2.nextLine();
        }
        for (int i = 0; i < count; i++) {
            for (int j = i + 1; j < count; j++) {
                if (str[i].compareTo(str[j]) > 0) {
                    temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        System.out.print("Các chuỗi sau khi sắp xếp là:");
        for (int i = 0; i <= count - 1; i++) {
            System.out.print(str[i] + ", ");
        }
    }
}
