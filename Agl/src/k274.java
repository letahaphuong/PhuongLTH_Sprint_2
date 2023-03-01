import java.util.HashMap;
import java.util.Map;

public class k274 {
    public static void main(String[] args) {
        Integer[] arr = {1, 23, 1, 23, 5, 52, 4};
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer item : arr) {
            if (!map.containsKey(item)){
                map.put(item, 1);
            }else {
                Integer num = map.get(item);
                map.replace(item, num+1);
            }

        }
        System.out.println(map);
    }
}
