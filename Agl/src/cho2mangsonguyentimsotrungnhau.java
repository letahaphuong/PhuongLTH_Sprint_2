import java.util.HashMap;
import java.util.Map;

public class cho2mangsonguyentimsotrungnhau {
    public static void main(String[] args) {
        int[] arr1 = {3, 1, 5, 6, 878, 45, 232, 23, 54, 3, 23, 54, 1, 3, 5, 6, 7, 4, 445, 454, 7, 4, 23, 7, 65, 34, 22, 3, 53, 2, 476, 8, 33, 44, 32, 888};
        int[] arr2 = {4, 54, 2, 1, 4, 545, 3, 23, 555, 442, 43, 87, 9, 8, 67, 56, 43, 23, 1, 2, 554, 4545, 33, 23, 545, 34, 24, 54, 32, 444, 888, 44};
        Map<Integer, Boolean> map = new HashMap<>();

        for (int item1 : arr1) {
            if (!map.containsKey(item1)) {
                map.put(item1, false);
            }
        }

        for (int item2 : arr2) {
            if (map.containsKey(item2)) {
                System.out.print(item2 + ";");
                map.remove(item2);
            }
        }

    }
}
