import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by Matthieu J.B Capuano on 11/26/2017.
 */
public class stringPracticeTestCases {
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {

    }

    @Test(timeout = TIMEOUT)
    public void printDupsTests() {
        stringPractice.printDups("fdkjg2");
    }

    @Test(timeout = TIMEOUT)
    public void anagCheckTests() {
        assertTrue(stringPractice.anagCheck("this", "sith"));
    }

    @Test(timeout = TIMEOUT)
    public void firstUniqCharTests() {
        assertEquals(0, stringPractice.firstUniqChar("leetcode"));
        assertEquals(2, stringPractice.firstUniqChar("loveleetcode"));
        System.out.println("HERREEEEEEE: " + stringPractice.firstUniqChar2("leetcode"));
        assertEquals(0, stringPractice.firstUniqChar2("leetcode"));
        assertEquals(2, stringPractice.firstUniqChar2("loveleetcode"));
        assertEquals(1, stringPractice.firstUniqChar3("abcdefghija"));
        assertEquals(0, stringPractice.firstUniqChar3("hello"));
        assertEquals(0, stringPractice.firstUniqChar3("Java"));
        assertEquals(1, stringPractice.firstUniqChar3("simplest"));
    }

    @Test(timeout = TIMEOUT)
    public void reverseStrTests() {
        assertEquals("GMO TSET A SI SIHT", stringPractice.reverseStr4("THIS IS A TEST OMG"));
    }

    @Test(timeout = TIMEOUT)
    public void printDupsWithCountTests() {
        stringPractice.printDupsWithCounts("THISSS ISSSS A POTATO!");
        System.out.println("---- Test Case 2 ----");
        stringPractice.printDupsWithCounts("abbcccddddeeeeeffffffggggggghhhhhhhhiiiiiiiiijjjjjjjjjj");
    }

    @Test(timeout = TIMEOUT)
    public void vowsAndConsTests() {
        System.out.println(stringPractice.vowsAndCons("aeiou123456AEIOUMMMMM"));
        assertEquals("Vowels: 0 Consonants: 15",
                stringPractice.vowsAndCons("mmmmMmmmmMmmmmM"));
        System.out.println(stringPractice.vowsAndCons("mmmmMmmmmMmmmmM"));
        assertEquals("Vowels: 3 Consonants: 9",
                stringPractice.vowsAndCons("thisisokkkkk"));
        System.out.println(stringPractice.vowsAndCons("thisisokkkkk"));
    }

    @Test(timeout = TIMEOUT)
    public void randomTest() {
        /*
        String s = "abcde";
        int i = 1;
        System.out.println(s);
        System.out.println(s.substring(0, 2));
         */
        Queue<String> q = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        q.offer("this");
        q.offer("is");
        q.offer("a");
        q.offer("test");
        q.offer("dfkjgsdfkjgsdfg");
        q.offer("fooobarrrrrr");
        q.offer("fooobarrrrrrbroooo");
        while (!q.isEmpty()) System.out.println(q.poll());

        Queue<Integer> q2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        q2.offer(23);
        q2.offer(34563546);
        q2.offer(453);
        q2.offer(6457457);
        q2.offer(55555);
        q2.offer(666666);
        q2.offer(999999999);
        while (!q2.isEmpty()) System.out.println(q2.poll());
    }
}
