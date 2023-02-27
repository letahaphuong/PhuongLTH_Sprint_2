public class chuoiStringSapxeptangdan {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder("asfdegerafdasgdfdfscv");

        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) > str.charAt(j)) {
                    char temp1 = str.charAt(i);
                    char temp2 = str.charAt(j);
                    str.deleteCharAt(i).insert(i, temp2);
                    str.deleteCharAt(j).insert(j, temp1);
                }
            }
        }
        System.out.println(str);
    }
}
