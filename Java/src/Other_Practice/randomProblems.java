package Other_Practice;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

public class randomProblems {

    public static void main(String[] args) {
        char[] firstKNonRepeatingTest1 = firstKNonRepeating("a", 1);
        char[] firstKNonRepeatingTest2 = firstKNonRepeating("aabcdefg", 1);
        char[] firstKNonRepeatingTest3 = firstKNonRepeating("aabbcdefghijkl", 2);
        char[] firstKNonRepeatingTest4 = firstKNonRepeating("abbbbbbbbbbbbbbbbbbc", 2);
        char[] firstKNonRepeatingTest5 = firstKNonRepeating("sajklhejklajliiibaaaaeez", 1);
        char[] firstKNonRepeatingTest6 = firstKNonRepeating("sajklhejklajliiibaaaaeez", 2);
        char[] firstKNonRepeatingTest7 = firstKNonRepeating("sajklhejklajliiibaaaaeez", 3);


        System.out.println(firstKNonRepeatingTest1);    // a
        System.out.println(firstKNonRepeatingTest2);    // b
        System.out.println(firstKNonRepeatingTest3);    // cd
        System.out.println(firstKNonRepeatingTest4);    // ac
        System.out.println(firstKNonRepeatingTest5);    // s
        System.out.println(firstKNonRepeatingTest6);    // sh
        System.out.println(firstKNonRepeatingTest7);    // shb

    }

    /**
     * PROBLEM: Find the first k non-repeating characters in a string in a single traversal.
     *
     * MY SOLUTION: Have two LinkedHashSets, one to keep track of characters you know you've already seen, and
     *              one to keep track of those you haven't seen repeat yet. Iterate through the string and
     *              check every time if you've seen that character before (in both sets). Edit them accordingly.
     *              Then iterate through the LinkedHashSet of non-repeating characters and return the first k.
     *
     * ALTERNATIVE SOLUTION: You could use a map to store a count for each character and the index of it's
     *                       first or last occurence. Then traverse the map and push the index of all characters
     *                       with count 1 in the min-heap (you could also just push the first k to reduce space
     *                       complexity). Then, pop the top k keys from the min heap and that is your first
     *                       k non-repeating characters in the string. This is a worst solution than mine
     *                       because it's O(n + nlog(n)) = O(nlog(n)), whereas my solution is O(n + k) = O(n)
     *
     *                       Found alternative solution at (I was specifically looking for heap-based solution):
     *                       https://www.techiedelight.com/first-k-non-repeating-characters-string/
     *
     */
    private static char[] firstKNonRepeating(String str, int k) {
        if ((k == 0) || k > str.length()) throw new InvalidParameterException();

        LinkedHashSet<Character> repeating = new LinkedHashSet<>();
        LinkedHashSet<Character> non_repeating = new LinkedHashSet<>();

        for (char c : str.toCharArray()) {
            if (non_repeating.contains(c)) {
                repeating.add(c);
                non_repeating.remove(c);
            } else if (!repeating.contains(c)){
                non_repeating.add(c);
            }
        }

        if (k > non_repeating.size()) throw new NoSuchElementException();

        char[] to_return = new char[k];

        Iterator iterator = non_repeating.iterator();
        for (int i = 0; i < k; i++) {
            to_return[i] = (char) iterator.next();
        }

        return to_return;
    }
}
