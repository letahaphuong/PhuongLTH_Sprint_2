import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class cho2mangsonguyentimsotrungnhau {
    public static void main(String[] args) {
        int[] arr1 = {3, 1, 5, 6, 878, 45, 232, 23, 54, 3, 23, 54, 1, 3, 5, 6, 7, 4};
        int[] arr2 = {4, 54, 2, 1, 4, 545, 3, 23, 555, 442};
        Map<Integer, Boolean> map = new HashMap<>();
        for (int item1 : arr1) {
            if (!map.containsKey(item1)) {
                map.put(item1, false);
                System.out.println(item1);
            }
        }

        for (int item2 : arr2) {
            if (map.containsKey(item2)) {
                System.out.print(item2 + ",");
                map.remove(item2);
            }
        }

    }
}
