import java.util.HashMap;
import java.util.Map;

public class k273 {
    public static void main(String[] args) {
        String str1 = "ahsdhadajhsd";
        String[] arr = str1.split("");
        Map<String, Integer> map = new HashMap<>();
        for (String item1 : arr) {
            if (!map.containsKey(item1)) {
                map.put(item1, 1);

            } else {
                Integer num = map.get(item1);
                map.replace(item1, num + 1);
            }
        }
        System.out.println(map);
    }
}

