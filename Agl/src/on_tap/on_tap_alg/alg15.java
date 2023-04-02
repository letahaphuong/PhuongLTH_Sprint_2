package on_tap.on_tap_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class alg15 {
    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        List list = new ArrayList();
        list.add(a);
        list.add(b);
        for (int i = list.size(); i >= 1; i--) {
            System.out.print(i);
        }
//        b = a + (b = a) - b;
//        a = b + (a = b) -a;
//
//        a = a * b;
//        b = a / b;
//        a = a / b;

//        a = a ^ b;
//        b = a ^ b;
//        a = a ^ b;
//        System.out.println(a);
//        System.out.println(b);
    }
}
