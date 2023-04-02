package oca_audit;

public class Test2 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        int i = 0;
        do {
            System.out.print(a[i]); // line1
            i++;
        } while (i < a.length - 1); // line2
        // kết quả 123 vì khi i chạy đến 3(line2) thì sẽ không thoả điều kiện i<3 thì vòng lặp sẽ kết thúc
    }
}
