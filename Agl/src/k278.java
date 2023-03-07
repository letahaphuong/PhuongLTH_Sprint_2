import java.util.Arrays;

public class k278 {
    public static void main(String[] args) {
        int[] arr = {1,5,2,3,4,9812398,123,19012393};
        Arrays.sort(arr);
        System.out.println(arr[arr.length-2]);
    }
}
