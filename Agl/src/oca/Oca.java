package oca;
public class Oca {
}

class Vehicle {
    int x;

    Vehicle() {
        this(10); // line 1
    }

    Vehicle(int x) {
        this.x = x; // line 2
    }
}

class Car extends Vehicle {
    int y;

    Car() {
        super(10); // line 3
    }

    Car(int y) {
        super(y); // line 4
        this.y = y; // line 5
    }

    @Override
    public String toString() {
        return super.x + ":" + this.y;
    }

    public static void main(String[] args) {
        Vehicle y = new Car(20); // khi khởi tạo đối tượng y = new Car(20) sẽ gọi constructor Car(int y){} với tham số là 20
        // tại line 4, line 5 y = 20,
        // sau khi super(20) thì constructor của Vehicle sẽ được override lại với tham số là 20 và khi đó x = 20
        // nên khi println ra kết quả sẽ là 20:20
        System.out.println(y);
    }
}