package on_tap.on_tap_alg;

public class alg12 {
//    public static void main(String[] args) {
//        int number = 987654321;
//        int rever = 0;
//        while (number > 0) {
//            rever = rever * 10 + number % 10;
//            number = number / 10;
//        }
//        System.out.println(rever);
//    }

    public static void main(String[] args) {
       int num = 10;
       String result = "";
       while (num > 0){
           result = num % 8 + result;
           num = num / 8;
       }
        System.out.println(result);
    }
}
