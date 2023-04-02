package on_tap.on_tap_alg;

import java.util.HashMap;
import java.util.Map;

public class alg8 {
    public static void main(String[] args) {
        int[] number = {1, 2, 1, 2, 1, 5, 6, 3, 31, 8,31};
        Map<Integer, Integer> map = new HashMap();
        for (int item : number) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                Integer num = map.get(item);
                map.put(item, num + 1);
            }
        }

        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (item.getValue() > 1) {
                System.out.println("key: " + item.getKey() + "Xuất hiên: " + item.getValue() + "lần");
            }
        }
    }
}
