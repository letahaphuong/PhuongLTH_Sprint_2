package on_tap.on_tap_alg;

import java.util.ArrayList;
import java.util.List;

public class alg14 {
    public static void main(String[] args) {

        String str = "abcnf";
        String[] strings = str.split("");
        List list = new ArrayList();
        for (int i = strings.length - 1; i >= 0; i--) {
            list.add(strings[i]);
        }
        System.out.println(list);
    }
}
