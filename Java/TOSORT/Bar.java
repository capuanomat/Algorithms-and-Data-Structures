/**
 * Created by Matthieu J.B Capuano on 10/20/2017.
 */
public class Bar extends Foo {
    public Bar() {
        this(99);
        callOneArg(2);
        System.out.println("Bar's no-arg constructor.");
    }

    public Bar(int i) {
        System.out.println("Bar's one arg constructor, " + i + ".");
    }

    public void doSomething() { System.out.println("Bar doSomething() was called"); }

    public void method1() { System.out.println("METHOD1 CALLED!!"); }
}
