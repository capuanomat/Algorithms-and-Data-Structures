package Data_Structures;
import Data_Structures.MyQueue;

public class DataStructuresTests {

    public static void main(String[] args) {
        System.out.println("***** Executing Data Structures Tests *****");

        DataStructuresTests tester = new DataStructuresTests();

        System.out.println("--- Queue Tests ---");
        tester.QueueTests();

        System.out.println("--- Stack Tests ---");
        tester.StackTests();
    }

    private void QueueTests() {
        MyQueue<Integer> queue = new MyQueue<>();   // queue = {}

        System.out.println(queue.isEmpty());        // true
        for (int i = 0; i < 5; i++) queue.add(i);   // queue = {0, 1, 2, 3, 4}
        System.out.println(queue.isEmpty());        // false
        System.out.println(queue.peek());           // 0
        System.out.println(queue.remove());         // 0
        System.out.println(queue.peek());           // 1
        System.out.println(queue.remove());         // 1
        System.out.println(queue.remove());         // 2
        System.out.println(queue.remove());         // 3
    }

    private void StackTests() {
        MyStack<String> stack = new MyStack<>();    // stack = {}

        String[] numbers = {"zero", "one", "two", "three", "four"};
        System.out.println(stack.isEmpty());    // true
        for (String number : numbers) {
            stack.push(number);                 // stack = {"zero", "one", "two", "three", "four"}
        }
        System.out.println(stack.isEmpty());    // false
        System.out.println(stack.peek());       // "four"
        System.out.println(stack.pop());        // "four"
        System.out.println(stack.peek());       // "three"
        System.out.println(stack.pop());        // "three"
        System.out.println(stack.pop());        // "two"
        System.out.println(stack.pop());        // "one"
    }
}
