package on_tap.on_tap_1;

public class ThirdImpl implements ThirdInterface{

    public boolean isNull(String string) {
        System.out.println("Impl Null Check");

        return string == null ? true : false;
    }

    public static void main(String args[]){
        ThirdImpl obj = new ThirdImpl();
        obj.print("");
        obj.isNull("");
    }
}
