public class k280 {
    public static void main(String[] args) {
        String str = "abvndf";
        String[] str2 = str.split("");
        String rev = "";
        for (int i = str2.length -1; i >=0 ; i--) {
            rev += str2[i];
        }
        System.out.println(rev);
    }
}
