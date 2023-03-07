import java.util.Arrays;

public class k281 {
    public static void main(String[] args) {
        String str = "le ta ha phuong";
        String[] str2 = str.split(" ");
        String result = "";
        System.out.println(Arrays.toString(str2));
        for (int i = str2.length -1; i >=0 ; i--) {
            result+= str2[i] + " ";
        }
        System.out.println(result);
    }
}
