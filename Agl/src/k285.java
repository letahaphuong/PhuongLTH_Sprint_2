public class k285 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 3, 1, 100};
        int max1 = arr[0];
        int max2 = arr[0];
        int max3 = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max1) {
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] > max2 && arr[i] < max1) {
                max2 = arr[i];
            }else if (arr[i] > max2 && arr[i])
        }
        System.out.println(max2);
    }
}