package Cracking_The_Coding_Interview.My_Solutions;

public class Ch1ArraysAndStrings {
    public static void main(String[] args) {

        String[] test_strings =  {"abcd", "aaaa", "abcad", "this is a test", "uniqe strg", "jb"};

        System.out.println("--- Running isUnique ---");
        for (String s: test_strings) {
            System.out.println(isUnique(s));
        }

        System.out.println("--- Running isUnique2 ---");
        for (String s: test_strings) {
            System.out.println(isUnique2(s));
        }

        System.out.println("--- Running oneEditAway ---");
        System.out.println(oneEditAway("this", "this"));
        System.out.println(oneEditAway("this", "thas"));
        System.out.println(oneEditAway("this", "thiss"));
        System.out.println(oneEditAway("this", "thi"));
        System.out.println(oneEditAway("this", "that"));
        System.out.println(oneEditAway("this", "t"));
        System.out.println(oneEditAway("tttt", "ttttt"));
        System.out.println(oneEditAway("tttt", "tttttt"));
        System.out.println(oneEditAway("", ""));
    }

    /**
     * QUESTION 1.1: isUnique
     *
     * DESCRIPTION: Implement an algorithm to determine if a string has all unique characters. What if you
     *              cannot use an additional data structure?
     *
     * HINTS: #44  ...
     *        #117 ...
     *        #132 ...
     */

    /**
     * SOLUTION: Time O(n), Space O(1)
     * -> NOTE: MY SOLUTION WAS VERY SIMILAR TO THE GIVEN ONE, so I just include mine only
     * - First thing to ask is if the string is given as an ASCII string or a Unicode string
     * - We assume that it is ASCII (if not we need more storage size) and CLARIFY THAT ASSUMPTION
     * - You could have an array of boolean values, where the flag at index i indicates whether character
     *   i in the alphabet is contained in the string. Return false the second time you see a character.
     * - Since Java chars use 7 bits, that's 128 possible difference characters, so if the string is longer than
     *   that you know that it has duplicates.
     *   -> You could also assume 256 for extended ASCII, CLARIFY THAT WITH THE INTERVIEWER
     *
     * NOTE: You could argue that the time complexity of the code is O(1) since the loop will never
     *       iterate through more than 128 characters (if it does there are duplicates).
     * @param str   Input string to check for uniqueness
     * @return      Whether the input string has all unique characters
     */
    private static boolean isUnique(String str) {
        if (str.length() > 128) return false;

        boolean[] seen = new boolean[128];
        for (char c : str.toCharArray()) {
            int c_as_int = (int) c;
            if (seen[c_as_int]) {
                return false;
            }
            seen[c_as_int] = true;
        }
        return true;
    }

    /**
     * SOLUTION 2:
     * "If you use a bit vector, you can reduce your space usage by a factor of eight."
     * Note that THIS IS ASSUMING ONLY LOWERCASE LETTERS 'a' through 'z'.
     *
     * I actually still don't fully understand this solution but I think some intuition might be:
     * - You have a bit vector to which you just gradually OR everything, but you left shift it by 1
     *   for some reason
     * WAIT, THIS ANSWER IS ACTUALLY WRONG! Think about the string "jb"
     * well "j" has ASCII 00001010 (10th letter in alphabet), so after the if statement checking becomes
     * 00010100. Then b comes along with ASCII 00000010, so the if condition checks
     * (00010100 & 00000100) = 00000100 > 0, so it will return false
     * WAIT BUT ACTUALLY IT WORKS, IT'S NOT WRONG AND I DON'T GET IT
     * @param str   Input string to check for uniqueness
     * @return      Whether the input string has all unique characters
     */
    private static boolean isUnique2(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    /**
     * SOLUTION 3: If you can't use additional data structures
     *
     * You could either
     * 1. Compare every character to every other character O(n^2)
     * 2. Sort the string in O(n log(n)) and linearly check the string for neighboring characters that are similar
     *    --> But many sorting algorithms take extra space
     * @param str   Input string to check for uniqueness
     * @return      Whether the input string has all unique characters
     */
    private static boolean isUnique3(String str) {
        // NOT IMPLEMENTED
        return false;
    }




    /**
     * QUESTION 1.2: Check Permutation (READ THROUGH, DIDN'T COMPLETE)
     *
     * DESCRIPTION: Given two strings, write a method to decide if one is a permutation of the other
     *
     * HINTS: #1, #84, #122, #131
     */

    /** MY SOLUTION:
     * ...
     *
     */

    /**
     * OFFICIAL SOLUTION 1: Sort the Strings
     * -> You should first understand if the permutation comparison is case sensitive: is God a permutation dog?
     * -> We should ask if whitespace is significant...
     *
     * -> First note that strings of different lengths cannot be permutations of each other
     *
     *
     * OFFICIAL SOLUTION 2: Check if the two strings have identical character counts
     * -> ...
     * -> They only iterate through each string once, and don't iterate through the arrays after... I'll let
     *    you figure out how ;)
     *
     * -> Answer: increment array during first iteration, decrement during the next and check if <0
     */





    /**
     * QUESTION 1.3: URLify READ THROUGH, DIDN'T COMPLETE)
     *
     * DESCRIPTION:
     *
     * HINTS: #53, #118
     */






    /**
     * QUESTION 1.4: Palindrome Permutation (READ THROUGH, DIDN'T COMPLETE)
     *
     * DESCRIPTION: Given a string, write a function to check if it is a permutation of a palindrome. A
     *              palindrome is a word or phrase that is the same forwards and backwards. A permutation is
     *              a rearrangement of letters. The palindrome does not need to be limited to just dictionary
     *              words.
     *
     * EXAMPLE:
     *          Input: Tact Coa
     *          Output: True (permutations: "taco cat", "atco cta", etc.)
     *
     * HINTS: #106, #121, #134, #136
     */





    /**
     * QUESTION 1.5: One Away
     *
     * DESCRIPTION: There are three types of edits that can be performed on strings: insert a character,
     *              remove a character, or replace a character. Given two strings, write a function to
     *              check if they are one edit (or zero edits) away.
     *
     * EXAMPLE:
     * pale,  ple  -> true
     * pales, pale -> true
     * pale,  bale -> true
     * pale,  bake -> true
     *
     * HINTS: ...
     *        ...
     *        ...
     *
     */

    /**
     * MY SOLUTION: This is my personal solution, and it turns out to be very similar to the official
     *           solution somehow. I prefer mine though.
     *
     * NOTE: I also return true if the strings are the same, they don't specify what you should actually
     *       do in that case.
     * @param first     First string
     * @param second    Second string
     * @return          Whether the two strongs are one operation away (add, remove, replace)
     */
    private static boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }

        boolean seen_diff = false;
        int i = 0, j = 0;
        while ((i < first.length() - 1) && (j < second.length() - 1)) {
            i = i < first.length() - 1 ? i + 1 : i;
            j = j < first.length() - 1? j + 1 : j;

            if ((first.charAt(i) != second.charAt(j)) && seen_diff) {
                return false;
            } else if (first.charAt(i) != second.charAt(j)) {
                seen_diff = true;
                if ((i == first.length() - 1) || (j == second.length() - 1)) {
                    return true;
                }
                i = (first.charAt(i + 1) == second.charAt(j)) ? i + 1 : i;
                j = (first.charAt(i) == second.charAt(j + 1)) ? j + 1 : j;
            }
        }
        return true;
    }

    /**
     * QUESTION 1.6-1.9: ...
     *
     * DESCRIPTION:
     *
     * HINTS:
     */
}