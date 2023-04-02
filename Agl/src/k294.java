import java.util.ArrayList;
import java.util.List;

public class k294 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> listB = new ArrayList<>();

        list.add("FB");
        list.add("FBb");
        list.add("FBbb");
        list.add("FBc");
        list.add("FB");
        list.add("FB");
        list.add("FB");
        System.out.println(list);
        int count = 0;
        for (String item: list) {
            if (item.equals("FB")){
                listB.add(item);
                count++;
            }
        }
        System.out.println(listB);
        System.out.println(count);
    }
}
