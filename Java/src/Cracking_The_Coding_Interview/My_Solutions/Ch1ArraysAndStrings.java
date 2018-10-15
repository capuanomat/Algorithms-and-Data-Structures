public class Ch1ArraysAndStrings {
    public static void main(String[] args) {

        String[] test_strings =  {"abcd", "aaaa", "abcad", "this is a test", "uniqe strg"};

        for (String s: test_strings) {
            System.out.println(isUnique(s));
        }
    }

    /**
     * QUESTION 1.1
     * Implement an algorithm to determine if a string has all unique characters. What if you cannot use an additional
     * data structure?
     *
     * HINTS:

     * From the book:
     * - First thing to ask is if the string is given as an ASCII string or a Unicode string
     * - We assume that it is ASCII (if not we need more storage size) and CLARIFY THAT ASSUMPTION
     * - Since Java chars use 7 bits, that's 128 possible difference characters, so if the string is longer than that
     *   you know that it has duplicates.
     *   -> You could also assume 256 for extended ASCII, CLARIFY THAT WITH THE INTERVIEWER
     * @param str
     * @return
     */
    public static boolean isUnique(String str) {
        if (str.length() > 128) {
            return false;
        }

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
         * QUESTION 1.1
         * Same as above but you can't use an extra data structure
         * @param str
         * @return
         */
    public static boolean isUnique2(String str) {
        return false;
    }

}