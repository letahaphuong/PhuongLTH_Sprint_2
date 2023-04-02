package oca_audit;

public class Test1 {
    public static void main(String[] args) {
        int n[][] = {{1, 3}, {2, 4}};
        for (int i = n.length - 1; i >= 0; i--) { // line 1
            for (int j = n[i].length - 1; j >= 0; j--) {// line 2
                System.out.print(n[i][j]);
            }
        }
        // line 1 duyệt từng phần tử của mảng lớn index[1] {2,4} -> line 2 duyệt từng phần tử của mảng con kết quả lần đầu là 42( vì chạy từ n[i].legth-1)
        // tiếp tục quay lại line1 duyêt phần tử index[0] {1,3} của mảng lớn -> line 2 duyệt từng phần tử của mảng con kết quả sẽ là 31 ( vì chạy từ n[i].legth-1)
    }
}
