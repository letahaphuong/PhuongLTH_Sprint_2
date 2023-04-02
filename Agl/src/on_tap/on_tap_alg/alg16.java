package on_tap.on_tap_alg;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class alg16 {
    //    public static void main(String[] args) {
//        int[] arr = {1, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 5, 3, 3, 3, 3, 4, 4, 4, 4};
//        int max1 = arr[0];
//        int max2 = arr[1];
//        int max3 = arr[2];
//
//        for (int i = 0; i < arr.length; i++) {
//            if (arr[i] > max1) {
//                max3 = max2;
//                max2 = max1;
//                max1 = arr[i];
//            } else if (arr[i] > max2 && arr[i] < max1) {
//                max3 = max2;
//                max2 = arr[i];
//            }
//            else if ( arr[i] < max2 && arr[i] > max3 ){
//                max3 = arr[i];
//            }
//        }
//        System.out.println(max3);
//    }
    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 2, 3, 4, 2, 2, 2, 3, 4, 5, 3, 3, 3, 3, 4, 4, 4, 4};
        Set set = new TreeSet();
        for (Integer i : arr
        ) {
            set.add(i);
        }
        System.out.println(Arrays.toString(set.toArray()));
        Object[] str = set.toArray();
        System.out.println(str[str.length - 3]);
    }
}
