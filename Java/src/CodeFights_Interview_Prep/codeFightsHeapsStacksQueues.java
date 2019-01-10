package CodeFights_Interview_Prep;

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.util.Stack;
import java.util.PriorityQueue;

/**
 * Created by Matthieu J.B Capuano on 12/31/2018.
 */
public class codeFightsHeapsStacksQueues {
    /* =============================== HEAPS, STACKS, AND QUEUES =============================== */

    /**
     *  PROBLEM: decodeString
     *
     *  NOTE: O(n) time required.
     *
     *  DESCRIPTION: Given an encoded string, return its corresponding decoded string.
     *              The encoding rule is: k[encoded_string], where the encoded_string inside the
     *              square brackets is repeated exactly k times. Note: k is guaranteed to be a
     *              positive integer.
     *              Note that your solution should have linear complexity because this is what
     *              you will be asked during an interview.
     *
     *  EXAMPLES: For s = "4[ab]", the output should be --> decodeString(s) = "abababab"
     *            For s = "2[b3[a]]", the output should be --> decodeString(s) = "baaabaaa"
     *            For s = "z1[y]zzz2[abc]", the output should be --> decodeString(s) = "zyzzzabcabc"
     *
     *  INPUT/OUTPUT: A string encoded as described above. It is guaranteed that:
     *                -> the string consists only of digits, square brackets and lowercase English letters;
     *                -> the square brackets form a regular bracket sequence;
     *                -> all digits that appear in the string represent the number of times the content in the brackets that follow them repeats, i.e. k in the description above;
     *                -> there are at most 20 pairs of square brackets in the given string.
     *                Guaranteed constraints:
     *                0 ≤ s.length ≤ 80.
     */

    /** MY SOLUTION (Took 2H35)
     * Go through the string, and every time you hit a "[", increment the counter accordingly
     * such that it points to whatever is immediately after [. Then recursively call the helper
     * function. Only return once you hit a "]", so that the parent call can continue appending
     * stuff after the recursive call is done.
     */
    static int index = 0;

    String decodeString(String s) {
        index = 0;
        return decodeStringHelper(s);
    }

    String decodeStringHelper(String s) {
        String str_recursed = "";
        while (index < s.length()) {
            char c = s.charAt(index);
            if (47 < (int) c && (int) c < 58) {
                int first_bracket = s.substring(index).indexOf("[");
                String number = s.substring(index, index + first_bracket);
                int repeat_times = Integer.parseInt(number);
                index = index + first_bracket + 1;
                str_recursed = str_recursed;// + decodeStringHelper(s).repeat(repeat_times);
            } else if (c == ']') {
                return str_recursed;
            } else {
                str_recursed = str_recursed + c;
            }
            index++;
        }

        return str_recursed;
    }

    /* TOP SOLUTION #1 */
    String decodeString2(String s) {
        Stack<Integer> intStack = new Stack();
        Stack<StringBuilder> strStack = new Stack();
        StringBuilder curr = new StringBuilder();
        int k = 0;

        for (char ch: s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(k);
                strStack.push(curr);
                curr = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = curr;
                curr = strStack.pop();
                for(int i = intStack.pop(); i > 0; i--) {
                    curr.append(tmp);
                }
            } else {
                curr.append(ch);
            }
        }
        return curr.toString();
    }


    /**
     *  PROBLEM: nextLarger
     *
     *  NOTE:
     *
     *  DESCRIPTION:
     *
     *  EXAMPLES:
     *
     *  INPUT/OUTPUT:
     */

    /* MY SOLUTION */
    int[] nextLarger(int[] a) {
        PriorityQueue<Node> min_queue = new PriorityQueue<>();

        int[] maximums = new int[a.length];

        int index = 0;
        while (index < a.length) {
            int value = a[index];
            Node node_curr = new Node(value, index);
            if (min_queue.size() == 0) {
                min_queue.offer(node_curr);
            } else {
                while ((min_queue.size() > 0)  && (value > min_queue.peek().getValue())) {
                    Node polled_node = min_queue.poll();
                    int polled_index = polled_node.getIndex();
                    if (maximums[polled_index] == 0) maximums[polled_index] = value;
                }
                min_queue.offer(node_curr);
            }
            index++;
        }

        for (int i = 0; i < maximums.length; i++) {
            if (maximums[i] == 0) maximums[i] = -1;
        }

        return maximums;
    }

    public class Node implements Comparable<Node> {
        public int value;
        public int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        public int getValue() { return value; }
        public int getIndex() { return index; }

        public int compareTo(Node to_compare) {
            if (this.value == to_compare.getValue()) {
                return 0;
            } else if (this.value > to_compare.getValue()) {
                return 1;
            } else {
                return -1;
            }
        }

        public String toString() {
            return String.format("(Value, Index): (%d, %d)", this.value, this.index);
        }
    }


    // [10, 3, 12, 4, 2, 9, 13, 0, 8, 11, 1, 7, 4, 5, 6]
    //   0  1   2  3  4  5   6  7  8  9  10 11 12 13
    //
    // [5, 6]
    //
    // [_, _, _, _, _, _, _, 8, 11, _, 7, _, 6, _]
    //
    //  Go backwards
    //      -> If number is smaller, put stack number there, then push onto stack
    //      -> If number is bigger, while bigger, remove numbers

    /* TOP SOLUTION 1 */
    int[] nextLarger2(int[] a) {
        int n = a.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>();
        for (int i = n-1; i>= 0; i--) {
            while (!stack.isEmpty() && a[i] >= stack.peek()) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(a[i]);
        }
        return result;
    }

    /* "TOP SOLUTION" 3 (One vote) */
    int[] nextLarger3(int[] a) {
        int [] result = new int[a.length];

        Stack<Integer> index = new Stack();
        for(int i = 0; i < a.length; i++){
            while(!index.isEmpty() && a[index.peek()] < a[i]){
                int ind = index.pop();
                result[ind] = a[i];
            }
            index.push(i);
        }

        while(!index.isEmpty()){
            result[index.pop()] = -1;
        }

        return result;
    }

    // PQ_values_min = {(8, 8), (13, 6)}
    //
    // [12, 12, 13, 9, 9, 13, _, 8, 11, _, 7, _, 6, _]


    /**
     *  PROBLEM: minimumOnStack
     *
     *  NOTE:
     *
     *  DESCRIPTION: ...
     *
     *  EXAMPLES:
     *
     *  INPUT/OUTPUT:
     */

    /* operations = ["push 10", "min", "push 5", "min", "push 8", "min", "pop", "min", "pop", "min"]
     *
     * example = ["p9", "p4", "p7", "p4", "p3", "p5", "p2"]
     *
     * min_heap = []
     *
     * stack = []
     * minimums = [9, 4, 4, 3, 3 ,3, 2, 3, 3, 3, 4]
     *
     *
     *
     * min_heap = [10]
     *
     * stack = [10]
     * minimums = [10, 5, 5, 5, 10]
     *
     * -> Iterate through array
     *      -> For push operations
     *          -> If min_heap is empty or if num <= min_heap.peek() -> NEW MIN, push to heap
     *          -> Either way, push to stack
     *      -> For pop
     *          -> pop from stack, then if element popped == min_heap.peek(), remove min_heap.poll()
     *      -> For min
     *          -> Append min_heap.peek() to result
     *
     */


    /**
     *  PROBLEM: countClouds
     *
     *  DESCRIPTION:
     *
     *  ...
     */

    // skyMap = [['0', '1', '1', '0', '1'],
    //
    //           ['0', '1', '1', '1', '1'],
    //                                 ^
    //           ['0', '0', '0', '0', '1'],
    //
    //           ['1', '0', '0', '1', '1']]
    //

    /*
     * SOLUTION 2: Create an array of HashSet, each HashSet will represent a cloud, then iterate through
     *  -> Whenever you hit a 1, check if it is one of the HashSet, if yes, do nothing
     *  -> If it is not in a HashMap, do a BFS from there
     *  -> BFS: Create a queue of indices to explore and a HashSet of indices explored, add neighbors of the 1 if
     *          they're 1
     *      -> Pop elements from the queue and, if the neighbors aren't in the explored HashSet and are 1, add them
     *      -> Everytime you pop a new 1 from the queue, if it isn't in the HashSet for the current cloud, then add it
     *
     *
     * SOLUTION 3:
     * arr<HashSets> arr = [{(0, 1), (0, 2), (1, 1), (1, 2), (1, 3)}, {(0, 4)}]
     */
}
