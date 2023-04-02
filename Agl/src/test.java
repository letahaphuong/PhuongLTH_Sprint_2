import java.util.*;

public class test {

    public static void main(String[] args) {

        int[] arr = {2,123,1,1,33,1,3};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[i]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));






//        int[] arr = {1, 3, 2, 5, 74, 3, 7, 5};
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = i + 1; j < arr.length; j++) {
//                if (arr[i] > arr[j]) {
//                    int temp = arr[i];
//                    arr[i] = arr[j];
//                    arr[j] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));



//        int[] arr = {1, 2, 3, 4, 7, 8, 9, 9, 9, 9, 7, 7, 7, 8, 8,};
//        int min1 = arr[0];
//        int min2 = arr[1];
//        int min3 = arr[2];
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] < min1){
//                min3 = min2;
//                min2 = min1;
//                min1 = arr[i];
//            }else if (arr[i] > min1 && arr[i]< min2){
//                min3 = min2;
//                min2 = arr[i];
//            }
//        }
//        System.out.println(min2);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập số cần kiểm tra: ");
//        int number = scanner.nextInt();
//        String result = "";
//        while (number> 0){
//            result = number % 8 + result;
//            number = number /8 ;
//        }
//        System.out.println(result);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập số nguyên tố: ");
//        int input = scanner.nextInt();
//        int number = 2;
//        for (int i = 2; i < input; i++) {
//            int count = 0;
//            for (int j = 1; j <= number; j++) {
//                if (number % j == 0){
//                    count++;
//                }
//            }
//            if (count == 2){
//                System.out.println(number);
//            }
//            number++;
//
//        }

//        int finbo1 = 0;
//        int finbo2 = 1;
//        int result = 0;
//        for (int i = 0; i < 100; i++) {
//            result = finbo1;
//            finbo1 = finbo2;
//            finbo2 = result + finbo1;
//            if (result >= 100){
//                break;
//            }
//            System.out.println(result);
//        }

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhập số cần kiểm tra: ");
//        int input = scanner.nextInt();
//        int number = 2;
//        for (int i = 2; i <= input; i++) {
//            int count = 0;
//            for (int j = 1; j <= number; j++) {
//                if (number % j == 0) {
//                    count++;
//                }
//            }
//            if (count == 2) {
//                System.out.print(number + ",");
//            }
//            number++;
//        }

//        String str = "aabacsdc";
//        String[] arr = str.split("");
//        Map<String, Integer> map = new HashMap();
//        for (int i = 0; i < arr.length; i++) {
//            if (!map.containsKey(arr[i])) {
//                map.put(arr[i], 1);
//            } else {
//                map.put(arr[i], map.get(arr[i]) + 1);
//            }
//        }
//        System.out.println(Arrays.toString(map.values().toArray(new Integer[0])));
    }

    public static void main1(String[] args) {
        int a = 0;
        int b = a + 1;
        int c;
        String text = a + "<br>" + b;
        for (int i = 0; i < 100; i++) {
            c = a + b;
            a = b;
            b = c;
            text = c + "<br>";
        }
        System.out.println(text);
    }
}

