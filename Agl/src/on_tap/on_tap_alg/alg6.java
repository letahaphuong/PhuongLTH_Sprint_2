package on_tap.on_tap_alg;

import java.util.*;

public class alg6 {
    public static void main(String[] args) {
        String str = "   Nguyen Van A   ";
        String[] str1 = str.split("");
        List list = new ArrayList();
        Map map = new HashMap<>();
        for (int i = 0; i < str1.length; i++) {
          if (str1[i].equals(" ")){
              list.add(str1[i]);
          }
        }

        System.out.println(list.size());
    }
}
