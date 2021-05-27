package CodeFightsInterview;

/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 */
public class codeFightsStrings {
    /**
     * PROBLEM: You have been given a string s, which is supposed to be a sentence. However,
     *          someone forgot to put spaces between the different words, and for some reason
     *          they capitalized the first letter of every word. Return the sentence after making
     *          the following amendments:
     *          - Put a single space between the words.
     *          - Convert the uppercase letters to lowercase.
     * EXAMPLES: ...
     * @param s
     * @return
     * SOLUTION 1
     */
    String amendTheSentence(String s) {
        char[] arr = s.toCharArray();
        String toRet = "";
        int count = 0;
        for (char c : arr) {
            int ascii = (int) c;
            if (((65 <= ascii) && (ascii <= 90))) {
                toRet += (count == 0) ?
                        ((char) (ascii + 32)) : (" " + (char) (ascii + 32));
            } else {
                toRet += c;
            }
            count++;
        }

        return toRet;
    }

    /** SOLUTION 1a: you can just do toRet.trim() instead of worrying about the extra space at
     *               the start in the event that the first character is caps.
     *               You could improve the solution below even further with elements from
     *               solution 3: using Characters.isUpperCase() and Characters.toLowerCase(c)
     */
    String amendTheSentence1a(String s) {
        String toRet = "";
        for (char c : s.toCharArray()) {
            int ascii = (int) c;
            if (((65 <= ascii) && (ascii <= 90))) {
                toRet += " " + (char) (ascii + 32);
            } else {
                toRet += c;
            }
        }

        return toRet.trim();
    }

    /** SOLUTION 2 **/
    // I think this uses a regular expression to split wherever there's a capital letter and
    // then rejoins by adding a " " and toLowerCase()ing the whole thing
    String amendTheSentence2(String s) {
        String[] split = s.split("(?=[A-Z])");
        return String.join(" ", split).toLowerCase();
    }

    /** SOLUTION 3 **/
    String amendTheSentence3(String s) {
        StringBuilder builder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if(Character.isUpperCase(c)){
                builder.append(" " + Character.toLowerCase(c));
            } else{
                builder.append("" + c);
            }
        }
        return builder.toString().trim();
    }

    /**
     * PROBLEM: findFirstSubstringOccurence
     * DESCRIPTION: Avoid using built-in functions to solve this challenge. Implement them
     *              yourself, since this is what you would be asked to do during a real interview.
     *
     *              Implement a function that takes two strings, s and x, as arguments and finds
     *              the first occurrence of the string x in s. The function should return an
     *              integer indicating the index in s of the first occurrence of x. If there are
     *              no occurrences of x in s, return -1.
     * @param s
     * @param x
     * @return
     */
    int findFirstSubstringOccurrence(String s, String x) {
    /* If build-ins where allowed, you would just do:
    return s.indexOf(x);
    */

    /* KMP Algorightm */
        int[] fTable = buildFailureTable(x);
        int i = 0;
        int j = 0;
        while (i <= (s.length() - x.length())) {
            while ((j < x.length()) && (s.charAt(i + j) == x.charAt(j))) {
                j++;
            }
            if (0 == j) {
                i++;
            } else {
                if (j == x.length()) {
                    return i;
                }
                int nextAlign = fTable[j - 1];
                i = i + j - nextAlign;
                j = nextAlign;
            }
        }
        return -1;
    }

    static int[] buildFailureTable(String x) {
        int[] fTable = new int[x.length()];
        fTable[0] = 0;

        int i = 0;
        int j = 1;
        while (j < x.length()) {
            if (x.charAt(i) == x.charAt(j)) {
                fTable[j++] = ++i;
            } else if ((x.charAt(i) != x.charAt(j) && (i != 0))) {
                i = fTable[i - 1];
            } else if ((x.charAt(i) != x.charAt(j) && (i == 0))) {
                fTable[j++] = 0;
            }
        }

        return fTable;
    }

    /* Brute-Force solution (works but too slow):
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == x.charAt(0)) {
            int j = 0;
            while ((j < x.length()) && (i + j < s.length())) {
                if ((i + j) < s.length()) {
                    if ((j == x.length() - 1) &&
                        (s.charAt(i + j) == (x.charAt(j)))) {
                        return i;
                    } else if (s.charAt(i + j) == (x.charAt(j))) {
                        j++;
                    } else {
                        j = x.length();
                    }
                }
            }
        }
    }
    return -1;
    */

    /**
     * PROBLEM
     * @param s
     * @return
     */
    String classifyStrings(String s) {
        int cons = 0;
        int vows = 0;
        boolean mixed = false;
        boolean tempmix = false;
        boolean prevVow = false;
        int lastQInd = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean vow = false;
            boolean question = false;
            if (c == '?') {
                question = true;
                tempmix = true;
                lastQInd = i;
            } else if ((c == 'a') || (c == 'e') || (c == 'i') ||
                    (c == 'o') || (c == 'u')) {
                vow = true;
                prevVow = true;
            }

            if (question) {
                cons++;
                vows++;
            } else if (vow) {
                vows++;
                if (!prevVow) {
                    tempmix = false;
                    lastQInd = 0;
                }
            } else {
                cons++;
                if (prevVow) {
                    tempmix = false;
                    lastQInd = 0;
                }
            }

            System.out.println("lastQInd: " + lastQInd + " vows: " + vows +
                    " cons: " + cons + " tempmix: " + tempmix);


            if (((cons == 5) || (vows == 3)) && tempmix) {
                mixed = true;
                if ((cons == 1) || (cons == 0)) {
                    cons = 0;
                    vows = i - lastQInd;
                } else {
                    vows = 0;
                    cons = i - lastQInd;
                }
                lastQInd = 0;
                tempmix = false;
            } else if (((cons == 5) || (vows == 3)) && !tempmix) {
                return "bad";
            }
        }

        return (mixed) ? "mixed": "good";
    }

}
