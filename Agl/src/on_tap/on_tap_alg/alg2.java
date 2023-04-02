package on_tap.on_tap_alg;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class alg2 {
    public static void main(String[] args) {
        String str = "aabacsdc";
        String[] str1 = str.split("");
        Map map = new HashMap();
        for (int i = 0; i < str1.length; i++) {
            if (!map.containsKey(str1[i])) {
                map.put(str1[i], 1);
            } else {
                Integer number = (Integer) map.get(str1[i]);
                map.put(str1[i], number + 1);
            }
        }
        System.out.println(Arrays.toString(new Map[]{map}));
    }
}
