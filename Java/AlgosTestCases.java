import com.sun.deploy.util.ArrayUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Matthieu J.B Capuano on 10/25/2017.
 */

public class AlgosTestCases {
    private static final int INTARRLENGTHS = 20;
    private Integer[] intMixed;
    private Integer[] intSorted;
    private Integer[] intSame;
    private Comparator<Integer> intComparator;
    private String[] strMixed;
    private String[] strSorted;
    private Comparator<String> strComparator;
    private static final int TIMEOUT = 200;

    /** ALGORITHM TEST CASES **/
    @Before
    public void setUp() {
        Random rand = new Random();
        intMixed = new Integer[INTARRLENGTHS];
        intSame = new Integer[INTARRLENGTHS];
        for (int i = 0; i < intMixed.length; i++) {
            intMixed[i] = rand.nextInt(999);
            intSame[i] = 8;
        }
        intSorted = intMixed.clone();
        Arrays.sort(intSorted);

        intComparator = new Comparators<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                incrementCount();
                return o1 - o2;
            }
        };

        strComparator = new Comparators<String>() {
            @Override
            public int compare(String o1, String o2) {
                incrementCount();
                return 0;
            }
        };
    }


    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        System.out.println("BUBBLE UNSORTED: " + Arrays.toString(intMixed));
        Algos.bubbleSort(intMixed, intComparator);
        System.out.println("BUBBLE SORTED:   " + Arrays.toString(intMixed) + "\n");
        assertArrayEquals(intSorted, intMixed);
        //assertTrue("Number of comparisons" + intComparator.getCount(), );
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        System.out.println("INSERTION UNSORTED: " + Arrays.toString(intMixed));
        Algos.insertionSort(intMixed, intComparator);
        System.out.println("INSERTION SORTED:   " + Arrays.toString(intMixed) + "\n");
        assertArrayEquals(intSorted, intMixed);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        System.out.println("SELECTION UNSORTED: " + Arrays.toString(intMixed));
        Algos.selectionSort(intMixed, intComparator);
        System.out.println("SELECTION SORTED:   " + Arrays.toString(intMixed) + "\n");
        assertArrayEquals(intSorted, intMixed);
    }

    @Test//(timeout = TIMEOUT)
    public void testMergeSort() {
        System.out.println("MERGE UNSORTED: " + Arrays.toString(intMixed));
        Algos.mergeSort(intMixed, intComparator);
        System.out.println("MERGE SORTED:   " + Arrays.toString(intMixed) + "\n");
        assertArrayEquals(intSorted, intMixed);
    }

    @Test//(timeout = TIMEOUT)
    public void testQuickSort() {
        System.out.println("QUICK UNSORTED: " + Arrays.toString(intMixed));
        Algos.quickSort(intMixed, intComparator);
        System.out.println("QUICK SORTED:   " + Arrays.toString(intMixed) + "\n");
        assertArrayEquals(intSorted, intMixed);
    }

    private abstract static class Comparators<T> implements Comparator<T> {
        private int count;

        public int getCount() {
            return count;
        }

        public void incrementCount() {
            count++;
        }
    }
}
