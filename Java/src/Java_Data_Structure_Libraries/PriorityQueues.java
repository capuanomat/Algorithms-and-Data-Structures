import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Iterator;
import java.util.Comparator;


class PriorityQueues {
    /*
    Documentation: https://docs.oracle.com/javase/8/docs/api/java/util/PriorityQueue.html

    Methods to know:
    - boolean add(E e)
    - boolean offer(E e)

    - E peek()                      --> Retrieves but does not remove head of Q, returns null if empty
    - E poll()                      --> Retrieves and removes the head of this queue, returns null if queue is empty

    - boolean contains(Object o)    --> Returns true if the object contains the specified element

    - void clear()                  --> Removes all elements from PQ

    NOTE: THE ITERATOR YOU GET FROM USING iterator() ISN'T GUARANTEED TO TRAVERSE THE ELEMENT IN ANY PARTICULAR
          ORDER, YOU HAVE TO ACTUALLY poll()

    - toArray(T[] a)
     */

    /*
    NOTES:
    - By default, it is a minHeap
    - For strings, it sorts alphabetically
     */

    public static void main(String[] args) {
        
        /* Basic Test with strings */
        Queue<String> pq = new PriorityQueue<>();

        pq.offer("this");
        pq.offer("is");
        pq.offer("a");
        pq.offer("test");
        pq.offer("as");
        pq.offer("banana");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        // Prints: "


        /* You can pass in your own comparator */
        // This comparator will place shorter strings at the top
        Comparator<String> stringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.length() - s2.length();
            }
        };

        Queue<String> pq2 = new PriorityQueue<>(stringLengthComparator);
        pq2.offer("smol");
        pq2.offer("thelongestboiiii");
        pq2.offer("longboiiii");
        pq2.offer("lesssmol");
        pq2.offer("verylongboiii");


        while (!pq2.isEmpty()) {
            System.out.println(pq2.poll());
        }

    }
}
