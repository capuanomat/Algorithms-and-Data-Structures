import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Matthieu J.B Capuano on 11/15/2017.
 * Important notes in preparing for interview and general Java programming.
 */
public class Notes {

    /*
    How to know when to make a function static: "'does it make sense to call this method, even if no Obj has been
    constructed yet?' If so, it should definitely be static."
    https://stackoverflow.com/questions/2671496/java-when-to-use-static-methods
     */

    /** IF YOU EVER NEED TO READ INPUT THAT'S PASSED INTO THE MAIN METHOD: **/
    Scanner reader = new Scanner(System.in);
    String input = reader.nextLine();
    char[] letters = input.toCharArray();

    /** You can't and don't need to use .equals for primitives, just use ==. Only use .equals
     *  for objects
     */

    /** A NOTE ON QUICKSORT AND MERGE SORT:
     *  Interesting note on quickSort and mergeSort with regards to stability:
     * - http://cafe.elharo.com/programming/java-programming/why-java-util-arrays-uses-two-sorting-algorithms/
     *  Basically, stability doesn't matter for primitive data types because you're not dealing
     *  with pointers, a 6 is the same as a 6 if they're both ints, whereas "6" and "6" may have
     *  different pointers depending on variable name. That's why Arrays.sort uses quickSort for
     *  primitives but mergeSort for objects. Apparently quickSort is faster.
     */

    /* ======= BINARY SEACH =======
    - Binary search only works on sorted arrays
    - Time Complexity: O(log(n) ) because you’re halving your data input on each pass
        - Not useful if the sorted array changes often 'because insertions and deletions are slow'
    - Space Complexity: O(1) because you just need a reference for the lower and upper bound, and current element
    */
    // BINARY SEARCH ON AN ARRAY:
    public <T> Node binarySearchArray(T[] arr, T value) {
        int min = 0;
        int max = arr.length - 1;

        while (min < max) {
            int mid = (min + max) / 2;
            //if (arr[min].compareTo)
        }
        return new Node("THIS NOT FINISHED");
    }

    /* ======= 2D Arrays ======= */
    public void example2D() {
        int[][] foo = new int[][]{
                new int[]{1, 2, 3},
                new int[]{1, 2, 3, 4},
        };

        System.out.println(foo.length); //2
        System.out.println(foo[0].length); //3
        System.out.println(foo[1].length); //4
    }

    /*
    == Adding a duplicate to a HashMap replaces the old value
    == Adding a duplicate to a HasSet doesn't insert the new value
     */

    /**
     * PROBLEM: containsDuplicates
     * @param a
     * @return
     * SOLUTION 1:
     */
    boolean containsDuplicates(int[] a) {
        if (null == a || a.length == 0) return false;

        Set<Integer> vals = new HashSet<Integer>();
        for (int curr: a) {
            if (vals.contains(curr)) return true;
            vals.add(curr);
        }
        return false;
    }

    // SOLUTION 2:
    boolean containsDuplicates2(int[] a) {
        Set<Integer> s = new HashSet<>();

        for (int b : a)
            s.add(b);

        return s.size() != a.length;
    }
    // NOTE: The above works because a set doesn't have duplicates


    /** Classes used for some of the notes above. */
    public class Node<T> {
        Node left;
        Node right;
        T data;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public Node(T data) {
            this.data = data;
        }
    }

    String joinWords(String[] words) {
        String sentence = "";
        for (String w : words) {
            sentence = sentence + w;
        }
        return sentence;
    }
}
