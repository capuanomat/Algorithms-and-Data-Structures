package 8_Other_Topics;

/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 */
public class OtherPracticeQuestions {
    /**
     * GOOGLE practice question.
     * PROBLEM: Something about formatting a key into the correct format. Where you had to
     *          uppercase everything, and add dashes after constant numbers, but the first number
     *          could have fewer.
     * @return
     */
    String formatKey(String str, int constant) {
        String str2 = str.replace("-", "");
        str2 = str2.replace("_", "");
        str2 = str2.replace(" ", "");
        str2 = str2.toUpperCase();
        int lFirst = str2.length() % constant;
        System.out.println(lFirst);

        int count = constant;
        String toRet = "";
        for (int i = 0; i < str2.length(); i++) {
            if (lFirst > 0) {
                toRet = toRet + str2.charAt(i);
                lFirst--;
                count = 0;
            } else if (count == 0){
                toRet = toRet + "-";
                count = constant;
                i--;
            } else {
                toRet = toRet + str2.charAt(i);
                count--;
            }
        }
        return toRet;
    }

    /**
     * CHALLENGE: isPangram
     * DESCRIPTION: Check if a sentence is a pangram or not.
     * EXAMPLE: ...
     * @param sentence
     * @return
     */
    boolean isPangram(String sentence) {
        sentence = sentence.toLowerCase();
        char c = 'a';
        while (c != '{') {
            if (sentence.indexOf(c) < 0) return false;

            c = (char) (c + 1);
        }
        return true;
    }
}
