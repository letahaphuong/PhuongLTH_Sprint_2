package oca_audit;

import java.util.Collections;

public class Test {
    int x, y;

    public Test(int x, int y) {
        initialize(x, y);
    }

    public void initialize(int x, int y) {
        this.x = x * x;
        this.y = y * y;
    }

    public static void main(String[] args) {
        int x = 9;
        int y = 5;
        Test obj = new Test(x, y);
        System.out.println(x + " " + y);
        // kết quả là 9 5 vì x,y là kiểu dữ liệu nguyên thuỷ.
        // KDL nguyên thuỷ sau khi đi ra khỏi phương thức sẽ không thay đổi nên vẫn giữ nguyên giá trị của x,y
    }
}
