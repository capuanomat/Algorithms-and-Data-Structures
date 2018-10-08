/**
 * Created by Matthieu J.B Capuano on 10/20/2017.
 */
import java.util.Comparator;

public class thatOne {
    static int i1 = 1;
    static thatOne t = null;
    static thatOne t2 = null;

    public static void main(String[] args) {

        System.out.println(i1);


        t.tryThis();
        t.seti1(55);
        t.tryThis();
        System.out.println("Hello " + i1);
        t2.tryThis();


        Integer i2 = 1000, i3 = 1000;
        System.out.println(i2.equals(i3));


        Foo f = new Foo();
        System.out.println(f.count);
        Foo f2 = new Foo(2);
        System.out.println(f.count);
        System.out.println(f2.count);
        Bar b = new Bar();
        Foo f3 = new Bar();

        new Bar();
        System.out.println(Foo.getCount());
        */
    }

    public static void tryThis() {
        System.out.println(i1);
    }

    public static void seti1(int i) {
        t.i1 = i;
    }
}
