import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class k291 {
    public static void main(String[] args) {
        int[] arr = {1,2,1,1,2,3,4,5,4};
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        System.out.println(Arrays.toString(set.toArray()));
    }
}
