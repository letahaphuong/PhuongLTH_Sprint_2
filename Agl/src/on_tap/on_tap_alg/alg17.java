package on_tap.on_tap_alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class alg17 {
    public static void main(String[] args) {
        List listA = new ArrayList();
        listA.add("Fb");
        listA.add("Fbx");
        listA.add("Fbd");
        listA.add("Fba");
        listA.add("Fbe");
        listA.add("Fb");
        List listB = new ArrayList();
        for (int i = 0; i < listA.size(); i++) {
            if (listA.get(i).equals("Fb")){
                listB.add(listA.get(i));
            }
        }
        System.out.println(Arrays.toString(listB.toArray()));
    }
}
