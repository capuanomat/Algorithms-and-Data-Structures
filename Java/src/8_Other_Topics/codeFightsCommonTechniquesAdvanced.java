package CodeFightsInterview;

import java.util.Arrays;

/**
 * Created by Matthieu J.B Capuano on 18/12/2019.
 */
public class codeFightsCommonTechniquesBasic {

    /**
     * PROBLEM: ...
     * EXAMPLE: ...
     * @param s
     * @return
     */
    String[] repeatedDNASequences(String s) {
        // Step 1: iterate through the string
        Set<String> repeating = new HashSet<>();
        int j;
        for (int i = 0; i < s.length() - 10; i++) {
            j = i + 10;
            String substr = s.substring(i, j);
            String remainder = s.substring(i + 1);
            int index = remainder.indexOf(substr);
            if (index != -1) {
                repeating.add(substr);
            }
        }

        // Step 2: Convert HashSet to String[]
        String[] to_return = new String[repeating.size()];
        int count = 0;
        for (String entry : repeating) {
            to_return[count++] = entry;
        }

        return Stream.of(to_return).sorted().toArray(String[]::new);
    }

}