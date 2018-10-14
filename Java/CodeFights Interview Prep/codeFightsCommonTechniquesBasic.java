package CodeFightsInterview;

import java.util.Arrays;

/**
 * Created by Matthieu J.B Capuano on 11/28/2017.
 */
public class codeFightsCommonTechniquesBasic {
    /**
     * PROBLEM: You have two integer arrays, a and b, and an integer target value v. Determine
     *          whether there is a pair of numbers, where one number is taken from a and the other
     *          from b, that can be added together to get a sum of v. Return true if such a pair
     *          exists, otherwise return false.
     * EXAMPLE: For a = [1, 2, 3], b = [10, 20, 30, 40], and v = 42, the output should be
     *          sumOfTwo(a, b, v) = true.
     * @param a
     * @param b
     * @param v
     * @return
     */
    boolean sumOfTwo(int[] a, int[] b, int v) {
        if ((null == a) || (null == b)) return false;

        Arrays.sort(a);
        Arrays.sort(b);
        int i = 0;
        int j = b.length - 1;

        while ((i < a.length) && (0 < j)) {
            if ((a[i] + b[j]) == v) {
                return true;
            } else if ((a[i] + b[j]) < v) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
