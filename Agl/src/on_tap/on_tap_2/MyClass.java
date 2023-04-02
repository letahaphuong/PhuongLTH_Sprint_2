package on_tap.on_tap_2;

public class MyClass implements SecondInterface{
    @Override
    public void secondMethod() {
        System.out.println("ok");
    }

    @Override
    public void log(String str) {
        SecondInterface.super.log(str);
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.secondMethod();
        myClass.log("");

    }

}
