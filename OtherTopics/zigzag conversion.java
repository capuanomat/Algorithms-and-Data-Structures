6. // ZIGZAG CONVERSION


class Solution {
    public String convert(String s, int numRows) {
        if (null == s)
            return null;

        if (numRows == 1)
            return s;

        /*
        OPTION 1: O(n|s|) space and time
        0. Create 2D string array to be populated by string:
        1. Take n characters -> Form a column
        2. For n - 2 subsequent characters -> They go up a row each time
        3. Repeat from 1.
        */
        /*
        char[][] arr = new char[numRows][s.length()]; // s.length() IS LONGER THAN NEEDED

        // Step 1: Populate 2D string
        int ptr = 0;
        int i = 0; // 1 2 3 2t 1t 0
        int j = 0; // 0 0 0 1t 2t 3
        boolean diagonal = false;
        while (ptr < s.length()) {
            System.out.println("(" + i + "," + j + ")");
            arr[i][j] = s.charAt(ptr++);
            if (diagonal) {
                if (i - 1 == 0) {
                    diagonal = false;
                } else if (i == 0) {
                    diagonal = false;
                    i += 2;
                    j--;
                }
                i--;
                j++;
            } else if (i + 1 == numRows) {
                i--;
                j++;
                diagonal = true;
            } else {
                i++;
            }

        }

        String zigzag = "";
        for (i = 0; i < arr.length; i++) {
            for (j = 0; j < arr[0].length; j++) {
                if (arr[i][j] != '\u0000')  {
                    zigzag = zigzag + arr[i][j];
                }
            }
        }
        System.out.println(zigzag);
        */

        /*
        OPTION 2: O(n) time, O(1) space
        - Find equation (function of size of zig-zag) to see if you can increment counter by same amount every time
        */
        int row = 0;
        int col = 0;
        int ptr = 0;

        int increment = (numRows) + (numRows - 2);
        
        String zigzag = "";

        while (zigzag.length() != s.length) {
            zigzag = zigzag + s.charAt(ptr);

            if ((ptr + increment) < s.length()) {
                ptr += increment;
            } else {
                row++;
                ptr = row;
                increment -= 2;
            }
        }

        return zigzag;
    }
}
