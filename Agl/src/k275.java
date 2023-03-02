public class k275 {
    public static void main(String[] args) {
        int num = 123;
        int rever =0;
      while (num != 0){
          int temp = num % 10;
          rever = rever * 10 + temp;

          num = num /10;
      }
        System.out.println(rever);
    }
}
