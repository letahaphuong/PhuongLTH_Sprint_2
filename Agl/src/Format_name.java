import java.util.Arrays;

public class Format_name {
    public static void main(String[] args) {
        String str = "      LE     ta      ha          phuonG        ";
        String str1 = str.trim().toLowerCase();
        String str2 = str1.replaceAll("\\s+", " ");
        char[] str3 = str2.toCharArray();
        boolean checkSpace = true;
        for (int i = 0; i < str3.length; i++) {
            if (Character.isLetter(str3[i])) {
                if (checkSpace) {
                    str3[i] = Character.toUpperCase(str3[i]);
                    checkSpace = false;
                }
            } else {
                checkSpace = true;
            }
        }
        String str4 = String.valueOf(str3);
        System.out.println(str4);
    }
}
