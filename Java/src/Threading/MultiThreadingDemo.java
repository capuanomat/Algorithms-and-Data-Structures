/**
 * REFERENCE: https://www.geeksforgeeks.org/multithreading-in-java/ :REFERENCE
 * "Multithreading is a Java feature that allows concurrent execution of two or more parts
 * of a program:
 * - This allos maximum utilization of CPU
 * - Each part of such a program is called a "thread"
 * - Threads are light-weight processes within a process
 *
 * You can create threads with two mechanisms:
 * 1. Extending the Thread class
 * 2. Implementing the Runnable Interface
 *
 * Important points:
 * 1. If we extend the Thread class, our class cannot extend any other class (since Java)
 *    doesn't support multiple inheritance). But if we implement the Runnable class, we
 *    can still extend other base classes.
 * 2. "We can achieve basic functionality of a thread by extending Thread class because
 *     it provides some inbuilt methods like yield(), interrupt(), etc. that are not
 *     available in Runnable interface.
 */
0
/**
 * METHOD 1: Extending the Thread class
 * - Create a class that extends the java.lang.Thread class
 * - This class overrides the run() method available in the Thread class
 * -- A thread begins its life inside the run() method
 * - You have to create an instance (object) of your new class and call the start() method
 *   to start the execution of a thread
 * -- start() invokes the run() method on the Thread object
 *
 * NOTE: Uncomment the class below and comment the others if wanting to demo it.
 */
/*
class MultiThreadingDemo extends Thread {

    public void run() {
        try {
            // Displaying the Thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running.");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exceptionis caught");
        }
    }
}
*/

/**
 * METHOD 2: Implementing the Runnable Interface
 * - Create a new class that implements the java.lang.Runnable interface
 * - Override the run() method
 * - Instantiate a Thread object and call the start() method on this object
 *
 * NOTE: Uncomment the class below and comment the others if wanting to demo it.
 */

class MultiThreadingDemo implements Runnable {

    public void run() {
        try {
            // Displaying the thread that is running
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}



























class ThreadingClassThingy implements Runnable {

    public ThreadingClassThingy() {
        //...
    }

    @Override
    public void run() {
        // Your task
        try {


            Thread.yield();
            // Thread.sleep(10); // Sleep 10 milliseconds
        } catch (Exception ex) {

        }
    }

}