package on_tap.on_tap_alg;

public class alg3 {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int fibo;
        String text = a + "" + b;
        for (int i = 0; i < 10; i++) {
            fibo = a + b;
            a = b;
            b = fibo;
            text += fibo + "";
        }
        System.out.println(text);
    }
}
