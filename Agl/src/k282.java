public class k282 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 1};
        for (int i = 0; i < arr.length / 2; i++) {
            if (arr[i] != arr[arr.length - i - 1]) {
                System.out.println("no");
                break;
            }
        }
        System.out.println("ok");
    }
}
