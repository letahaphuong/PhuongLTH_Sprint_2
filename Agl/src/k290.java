import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class k290 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 2, 4, 6, 7, 8, 9};
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        System.out.println(Arrays.toString(set.toArray()));
    }
}
