package Other_Practice;

import java.util.*;

/**
 * Created by Matthieu J.B Capuano on 11/26/2017.
 * This document contains my solutions to the problems at the following link, while practicing for a google coding sample:
 * http://www.java67.com/2014/03/how-to-find-duplicate-characters-in-String-Java-program.html
 */
public class stringPractice {

    /******************************** PROBLEM 1 ********************************/
    /**
     * PROBLEM: Write a function that prints every every duplicate character in a string once.
     *          (As in, if a character repeats multiple times, it prints it once)
     * @param str
     * MY SOLUTION:
     */
    static void printDups(String str) {
        Set<Character> st = new HashSet<>();
        Set<Character> printed = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (st.contains(c)) {
                if (!printed.contains(c)) {
                    System.out.println(c);
                    printed.add(c);
                }
            } else {
                st.add(c);
            }
        }
    }

    /******************************** PROBLEM 2 ********************************/
    /**
     * PROBLEM: Check if two strings are annagrams of each other.
     * @param str1
     * @param str2
     * @return
     */
    static boolean anagCheck(String str1, String str2) {
        char[] c1 = str1.toCharArray();
        Arrays.sort(c1);
        char[] c2 = str2.toCharArray();
        Arrays.sort(c2);

        return Arrays.equals(c1, c2);
    }

    /******************************** PROBLEM 3 ********************************/
    /**
     * PROBLEM: Return the index of the first non-repeating character, if there are none return -1.
     * @param s
     * @return
     * SOLUTION 1:
     */
    static int firstUniqChar(String s) {
        Map<Character, Integer> mp = new LinkedHashMap<>();

        for (int i = 0; i < s.length(); i++) {
            /* NOTE: You can replace all the code in this for loop with the following line:
                    counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
             */
            char c = s.charAt(i);
            if (!mp.containsKey(c)){
                mp.put(c, 1);
            } else {
                mp.put(c, (mp.get(c) + 1));
            }
        }

        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            if (entry.getValue().equals(1)) {
                return s.indexOf((char) entry.getKey());
            }
        }
        return -1;
    }

    /** SOLUTION 2: If you need to use a HashMap instead of a LinkedHashMap, you can do the same
     *              thing as above but instead of iterating through the map in the second for
     *              loop, just iterate through the string again.
     */
    static int firstUniqChar2(String s) {
        Map<Character, Integer> mp = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!mp.containsKey(c)){
                mp.put(c, 1);
            } else {
                mp.put(c, (mp.get(c) + 1));
            }
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mp.get(c) == 1) {
                return i;
            }
        }
        return -1;
    }

    /** SOLUTION 3: BEST SOLUTION IN MY OPINION**/
    static int firstUniqChar3(String s) {
        s = s.toLowerCase(); // Technically we should worry about it but solution would be similar
        int freq [] = new int[26];
        for(int i = 0; i < s.length(); i ++)
            freq [s.charAt(i) - 'a'] ++;

        for(int i = 0; i < s.length(); i ++)
            if(freq [s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }

    /** SOLUTION 4: Uses two data structures to keep track of repeating and non-repeating
     *              characters.
     *
     */
    public static char firstNonRepeatingChar(String word) {
        Set<Character> repeating = new HashSet<>();
        List<Character> nonRepeating = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if (repeating.contains(letter)) {
                continue; //Skip the rest of the for loop
            }
            if (nonRepeating.contains(letter)) {
                nonRepeating.remove((Character) letter);
                repeating.add(letter);
            } else {
                nonRepeating.add(letter);
            }
        }
        return nonRepeating.get(0);
    }

    // More solutions to this problem in codeFightsArrays

    /******************************** PROBLEM 4 ********************************/
    /**
     * PROBLEM: Reverse a String
     * @param str
     * @return
     * SOLUTION 1
     */
    static String reverseStr(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            s.push(str.charAt(i));
        }
        String toRet = "";
        while (!s.isEmpty()) toRet = toRet + s.pop();
        return toRet;
    }

    /** SOLUTION 2 (If StringBuilder is allowed)**/
    static String reverseStr2(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /** SOLUTION 3 **/
    public static String reverseStr3(String input){
        char[] in = input.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

    /** SOLUTION 4 **/
    public static String reverseStr4(String str) {
        char[] strChars = str.toCharArray();

        String toRet = "";
        for (int i = strChars.length - 1; i >= 0; i--) {
            toRet += strChars[i];
        }

        return toRet;
    }

    /** SOLUTION 5: GOOD SOLUTION! (Recursive) **/
    public static String reverseStrRecursively(String str) {
        //base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }
        return reverseStrRecursively(str.substring(1)) + str.charAt(0);
    }


    /******************************** PROBLEM 5 ********************************/


    /******************************** PROBLEM 6 ********************************/
    public static void printDupsWithCounts(String str) {
        Map<Character, Integer> mp = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            mp.put(c, mp.containsKey(c) ? mp.get(c) + 1 : 1);
        }
        for (Map.Entry<Character, Integer> entry : mp.entrySet()) {
            if (entry.getValue() > 1)
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
    // NOTE: You could also convert str.toCharArray() and use a for-each loop for the first loop

    /******************************** PROBLEM 7 ********************************/
    public static String vowsAndCons(String str) {
        str = str.toLowerCase();
        int vows = 0;
        int cons = 0;
        for (char c : str.toCharArray()) {
            if ((c == 'a') || (c == 'e') || (c == 'i') || (c == 'o') || (c == 'u')) {
                vows++;
            } else if ((97 <= c) && (c <= 'z')) {
                cons++;
            }
        }
        return "Vowels: " + vows + " Consonants: " + cons;
    }
    // Their solution is essentially the same but with a switch statement and only countin vowels

    /******************************** PROBLEM 8 ********************************/

}
