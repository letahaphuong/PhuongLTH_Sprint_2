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
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập thông tin: ");
        String str = scanner.nextLine();
        str = str.toUpperCase();
        System.out.println(str);

        String[] str1 = str.split("");
        System.out.println(Arrays.toString(str1));

        Stack<String> temp = new Stack<>();
        for (int i = 0; i < str1.length; i++) {
            temp.push(str1[i]);
        }

        Map<String, Integer> map = new TreeMap<>();
        for (int i = 0; i < temp.size(); i++) {
            if (!map.containsKey(temp.get(i))) {
                map.put(temp.get(i), 1);
            } else {
                map.put(temp.get(i), map.get(temp.get(i)) + 1);
            }
        }
    }
}
