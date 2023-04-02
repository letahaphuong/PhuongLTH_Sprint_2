package on_tap.on_tap_alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class alg18 {
    // Thuật toán về lấy các giá trị không trùng lặp.
    public static void main(String[] args) {
        int[] numb = {1, 1, 1, 2, 3, 4, 5, 4, 5, 4, 5, 6, 7, 8, 6, 5, 4, 23};
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numb.length; i++) {
            if (!map.containsKey(numb[i])) {
                map.put(numb[i], 1);
            } else {
                Integer number = map.get(numb[i]);
                map.put(numb[i], number + 1);
            }
        }



        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() == 1){
                System.out.print(item.getKey()+",");
            }
        }

    }
}
