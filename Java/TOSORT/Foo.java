/**
 * Created by Matthieu J.B Capuano on 10/20/2017.
 */
public class Foo {
    static int count = 0;
    public Foo() {
        System.out.println("Foo no-arg constructor.");
        //doSomething();
    }

    public Foo(int i) {
        count += i;
    }

    public void callOneArg(int i) {
        new Foo(i);
    }

    public static int getCount() { return count; }

    public void doSomething() {
        System.out.println("Foo doSomething() was called");
    }

    public void method1() { System.out.println("METHOD1 CALLED!!"); }
}
