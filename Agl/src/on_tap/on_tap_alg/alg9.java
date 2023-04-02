package on_tap.on_tap_alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class alg9 {
    public static void main(String[] args) {
        String str = "aabacsdc";
        String[] strings = str.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String item : strings) {
            if (!map.containsKey(item)) {
                map.put(item, 1);
            } else {
                Integer number = map.get(item);
                map.put(item, number + 1);
            }
        }
        System.out.println(Arrays.toString(new Map[]{map}));
    }
}
