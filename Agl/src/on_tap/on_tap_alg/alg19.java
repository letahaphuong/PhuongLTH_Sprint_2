package on_tap.on_tap_alg;

public class alg19 {
    public static void main(String[] args) {

            String str = "    LE TA     ha          PhuonG       ";
            String str1 = str.toLowerCase().trim();
            String str2 = str1.replaceAll("\\s+", " ");
            System.out.println(str2);
            char[] str3 = str2.toCharArray();
            boolean foundSpace = true;
            for (int i = 0; i < str3.length; i++) {
                if (Character.isLetter(str3[i])) {
                    if (foundSpace) {
                        str3[i] = Character.toUpperCase(str3[i]);
                        foundSpace = false;
                    }
                } else {
                    foundSpace = true;
                }
            }
            String str4 = String.valueOf(str3);
            System.out.println(str4);

    }
}
