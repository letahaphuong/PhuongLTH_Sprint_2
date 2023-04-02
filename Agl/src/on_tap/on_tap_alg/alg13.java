package on_tap.on_tap_alg;

public class alg13 {
    //    public static void main(String[] args) {
//        int[] arr = {1, 2, 32, 22, 22, 22, 1, 6, 7, 8};
//        int max1 = arr[0];
//        int max2 = arr[1];
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > max1){
//                max2 = max1;
//                max1 = arr[i];
//            }else if (arr[i] > max2 && arr[i]< max1){
//                max2 = arr[i];
//            }
//        }
//        System.out.println("max2" + max2);
//        System.out.println("max1" + max1);
//    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 32, 22, 22, 22, 1, 6, 7, 8};
        int min1 = arr[0];
        int min2 = arr[1];
        for (int item : arr) {
            if (item < min1) {
                min2 = min1;
                min1 = item;
            } else if (item > min1 && item < min2) {
                min2 = item;
            }
        }
        System.out.println(min2);
    }
}
