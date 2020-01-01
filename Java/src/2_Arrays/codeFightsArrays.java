package CodeFights_Interview_Prep;

import java.util.Arrays;

/**
 * Created by Matthieu J. Capuano on 11/27/2017.
 */
public class codeFightsArrays {
    /* =============================== ARRAYS =============================== */

    /**
     * PROBLEM: firstDuplicate
     *
     * NOTE: O(n) time, O(1) space
     *
     * DESCRIPTION: Given an array a that contains only numbers in the range from 1 to a.length,
     *              find the first duplicate number for which the second occurrence has the minimal
     *              index. In other words, if there are more than 1 duplicated numbers, return the
     *              number for which the second occurrence has a smaller index than the second
     *              occurrence of the other number does. If there are no such elements, return
     *              -1.
     *
     * EXAMPLES: For a = [2, 3, 3, 1, 5, 2], the output should be: firstDuplicate(a) = 3.
     *              - There are 2 duplicates: numbers 2 and 3. The second occurrence of 3 has a
     *                smaller index than than second occurrence of 2 does, so the answer is 3.
     *           For a = [2, 4, 3, 5, 1], the output should be firstDuplicate(a) = -1.
     * @param a
     * @return
     */
    /* MY SOLUTION */
    int firstDuplicate(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int val = Math.abs(a[i]);
            if (a[val-1] < 0) {
                return val;
            }
            a[val - 1] = -a[val - 1];
        }
        return -1;
    }

    /* PERSONAL ADDITIONAL CHALLENGE: What if entries in the array can also be zero? */
    // If you use the previous approach, this is just the pigeonhole principle since you're representing
    // (a.length + 1) possible values in an array of size (a.length). I think you could use the
    // same approach as above but just have one extra variable to keep track of whether a zero has been
    // encountered (kind of like an extra index.
    //
    // You could also do this out of place using a hashset to keep track of entries you've already seen.
    // THIS SOLUTION IS NOW VALID WITHOUT LIMIT ON THE ENTRIES IN THE ARRAY.
    int firstDuplicate2(int[] a) {
        Set<Integer> seen = new HashSet<>();
        for (int curr : a) {
            if (seen.contains(curr)) {
                return curr;
            }
            seen.add(curr);
        }
        return -1;
    }


    /**
     * PROBLEM: firstNotRepeatingCharacter
     *
     * NOTE: Iterate through string once, and only use O(1) space
     *
     * DESCRIPTION: Given a string s consisting of small English letters, find and return
     *              the first instance of a non-repeating character in it.
     *              If there is no such character, return '_'.
     *
     * EXAMPLES: For s = "abacabad", the output should be firstNotRepeatingCharacter(s) = 'c'.
     *           ...
     *           For s = "abacabaabacaba", the output should be firstNotRepeatingCharacter(s) = '_'.
     *
     * @param s
     * @return
     */
    /* MY SOLUTION */
    public static final int NUM_OF_CHARS = 26;
    char[] alphabet = new char[NUM_OF_CHARS];
    int[] indices = new int[NUM_OF_CHARS];

    char firstNotRepeatingCharacter(String s) {
        char curr;
        for (int i = 0; i < s.length(); i++) {
            curr = s.charAt(i);
            alphabet[((int) curr) - 97] += 1;
            if (indices[((int) curr) - 97] == 0) {
                indices[((int) curr) - 97] = i;
            }
        }
        int minIndex = 100001;
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            if ((alphabet[i] == 1) && (indices[i] < minIndex)) {
                minIndex = indices[i];
            }
        }
        return (minIndex != 100001) ? s.charAt(minIndex) : '_';
    }

    /* TOP SOLUTION 1 */
    char firstNotRepeatingCharacter2(String s) {
        char[] c = s.toCharArray();
        for(int i=0; i < s.length(); i++) {
            if(s.indexOf(c[i]) == s.lastIndexOf(c[i]))
                return c[i];
        }
        return '_';
    }

    /* TOP SOLUTION 2 */
    char firstNotRepeatingCharacter3(String s) {
        int[] counter = new int[26];

        for (char c : s.toCharArray()) counter[c - 'a']++;

        for (char c : s.toCharArray()) {
            if (counter[c - 'a'] == 1) return c;
        }

        return '_';
    }


    /**
     * PROBLEM: rotateImage
     *
     * NOTES: Solve this task in-place, using O(1) space
     *
     * DESCRIPTION: You are given an n x n 2D matrix that represents an image. Rotate the image by
     *              90 degrees (clockwise).
     *
     * EXAMPLE: For
     *              a = [[1, 2, 3],
     *                   [4, 5, 6],
     *                   [7, 8, 9]]
     *          the output should be:
     *              rotateImage(a) =
     *                  [[7, 4, 1],
     *                   [8, 5, 2],
     *                   [9, 6, 3]]
     *
     * @param a
     * @return
     */
    /* MY SOLUTION */
    int[][] rotateImage(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < (a.length - 1 - i); j++) {
                int temp = a[i][j];
                a[i][j] = a[a.length - j - 1][a.length - i - 1];
                a[a.length - j - 1][a.length - i - 1] = temp;
            }
        }

        for (int i = 0; i < (a.length/2); i++) {
            for (int j = 0; j < a.length; j++) {
                int temp = a[i][j];
                a[i][j] = a[a.length - i - 1][j];
                a[a.length - i - 1][j] = temp;
            }
        }

        return a;
    }

    /* TOP SOLUTION 1 */
    int[][] rotateImage2(int[][] a) {
        int n = a.length;
        for(int i = 0; i < n / 2; i++){
            for(int j = i; j < n-i-1; j++){
                int temp = a[i][j];
                a[i][j] = a[n-j-1][i];
                a[n-j-1][i] = a[n-1-i][n-1-j];
                a[n-1-i][n-1-j] = a[j][n-1-i];
                a[j][n-1-i] = temp;
            }
        }
        return a;
    }

    /**
     * PROBLEM: sudoku2
     *
     * NOTE: None
     *
     * DESCRIPTION: Implement an algorithm that will check whether the given  grid of
     *              numbers represents a valid Sudoku puzzle according to the layout
     *              rules described above. Note that the puzzle represented by grid
     *              does not have to be solvable.
     *
     * EXAMPLES: ...
     */
    boolean sudoku2(char[][] grid) {
        boolean[] rowChk;
        boolean[][] numChk = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            rowChk = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (!(grid[i][j] == '.')) {
                    int val = grid[i][j] - '0';
                    if (rowChk[val - 1]) return false;
                    rowChk[val - 1] = true;

                    if (numChk[val - 1][j]) return false;
                    numChk[val - 1][j] = true;

                    int i1, i2, i3 = i;
                    if (i % 3 == 0) {
                        i1 = i + 1;
                        i2 = i + 2;
                    } else if (i % 3 == 1) {
                        i1 = i - 1;
                        i2 = i + 1;
                    } else {
                        i1 = i - 1;
                        i2 = i - 2;
                    }

                    int j1, j2, j3 = j;
                    if (j % 3 == 0) {
                        j1 = j + 1;
                        j2 = j + 2;
                    } else if (j % 3 == 1) {
                        j1 = j - 1;
                        j2 = j + 1;
                    } else {
                        j1 = j - 1;
                        j2 = j - 2;
                    }

                    if ((grid[i1][j1] == grid[i][j]) ||
                            (grid[i1][j2] == grid[i][j]) ||
                            (grid[i1][j3] == grid[i][j]) ||
                            (grid[i2][j1] == grid[i][j]) ||
                            (grid[i2][j2] == grid[i][j]) ||
                            (grid[i2][j3] == grid[i][j]) ||
                            (grid[i3][j1] == grid[i][j]) ||
                            (grid[i3][j2] == grid[i][j]))
                    {
                        return false;
                    }

                }
            }
        }
        return true;
    }


    /**
     * PROBLEM: isCryptSolution
     *
     * DESCRIPTION: A cryptarithm is a mathematical puzzle for which the goal is to find
     *              the correspondence between letters and digits, such that the given
     *              arithmetic equation consisting of letters holds true when the letters
     *              are converted to digits.
     *
     *              You have an array of strings crypt, the cryptarithm, and an an array
     *              containing the mapping of letters and digits, solution. The array
     *              crypt will contain three non-empty strings that follow the structure:
     *              [word1, word2, word3], which should be interpreted as the
     *              word1 + word2 = word3 cryptarithm.
     *
     *              If crypt, when it is decoded by replacing all of the letters in the
     *              cryptarithm with digits using the mapping in solution, becomes a
     *              valid arithmetic equation containing no numbers with leading zeroes,
     *              the answer is true. If it does not become a valid arithmetic
     *              solution, the answer is false.
     *
     * EXAMPLES: For crypt = ["SEND", "MORE", "MONEY"] and
     *           solution = [['O', '0'],
     *                       ['M', '1'],
     *                       ['Y', '2'],
     *                       ['E', '5'],
     *                       ['N', '6'],
     *                       ['D', '7'],
     *                       ['R', '8'],
     *                       ['S', '9']]
     *          the output should be isCryptSolution(crypt, solution) = true.
     *          When you decrypt "SEND", "MORE", and "MONEY" using the mapping given in
     *          crypt, you get 9567 + 1085 = 10652 which is correct and a valid arithmetic
     *          equation.
     *          ...
     *
     * @param crypt
     * @param solution
     * @return
     */
    // My own solution to this was in Python3, didn't bother rewriting it in Java //

    /* SOLUTION 2 */
    boolean isCryptSolution2(String[] crypt, char[][] solution) {
        for(char[] arr : solution){
            for(int i = 0; i < crypt.length; i++){
                crypt[i]=crypt[i].replace(arr[0],arr[1]);
            }
            System.out.println(Arrays.toString(crypt));
        }

        for(int i = 0; i < crypt.length; i++){
            if(!crypt[i].equals("0") && crypt[i].startsWith("0"))
                return false;
        }

        return Long.parseLong(crypt[0]) + Long.parseLong(crypt[1]) ==
                Long.parseLong(crypt[2]);
    }
}
