/**
 * Main class to run example scripts for Multithreading in Threading package
 */

public class MultiThreadingDemoMain {
    public static void main(String[] args) {

        int n = 15;  // Number of threads

        /* For METHOD 1
        for (int i = 0; i < n; i++) {
            MultiThreadingDemo object = new MultiThreadingDemo();
            object.start();
        }
        */

        /* For METHOD 2 */
        for (int i = 0; i < n; i++) {
            Thread object = new Thread(new MultiThreadingDemo());
            object.start();
        }
    }
}