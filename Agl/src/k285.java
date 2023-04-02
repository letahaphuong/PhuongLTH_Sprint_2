public class k285 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 7, 8, 4, 5, 7, 8, 7, 8, 5, 5};
        int max1 = arr[0];
        int max2 = arr[1];
        int max3 = arr[2];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            } else if (arr[i] < max1 && arr[i] > max2) {
                max3 = max2;
                max2 = arr[i];
            } else if (arr[i] < max2 && arr[i] > max3) {
                max3 = arr[i];
            }
        }
        System.out.println(max3);
    }
}