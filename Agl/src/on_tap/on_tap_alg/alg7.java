package on_tap.on_tap_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class alg7 {
    public static void main(String[] args) {
        String str = "llkasdjakjsd";
        String str2 = "abcdefghijklmnopqrstuvwxyz";
        String []arr1 = str.split("");
        String []arr2 = str2.split("");
        List arr3 = new ArrayList();
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if (Objects.equals(arr1[j], arr2[i])){
                    arr3.add(arr1[j]);
                }
            }
        }
        System.out.println(Arrays.toString(new List[]{arr3}));
    }

}
