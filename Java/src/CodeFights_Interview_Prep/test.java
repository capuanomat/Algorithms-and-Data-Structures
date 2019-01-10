package CodeFights_Interview_Prep.test;

import java.util.Stack;

public class test {
    public static void main(String[] args) {
        String str = "/home/a/./x/../b//c/";

        String[] split = str.split("/");
        System.out.println(split.length);

        Stack<String> steck = new Stack<>();
        steck.push("this");
        steck.push("is");
        steck.push("a");
        steck.push("test");

        System.out.println(String.join("/", steck));

        System.out.println("0123456789".substring(4));

        System.out.println("4[ab]");
        System.out.println("2[b3[a]]");
        System.out.println("z1[y]zzz2[abc]");
        System.out.println("3[a]2[bc]");

        System.out.println("-------------------");
        int[][] arr = new int[4][5];
        System.out.println(arr.length);

        int[][] arr2 = {{1, 2, 3},
                        {4, 5, 6}};
        System.out.println(arr2[0][1]);
        System.out.println(arr2[1][2]);
    }

    static int index = 0;

    String decodeString(String s) {
        String s2 = new String(s);
        String str_recursed = "";
        while (!(s.charAt(index) == ']') && index < s.length()) {
            char c = s.charAt(index);
            if (47 < (int) c && (int) c < 58) {
                int first_bracket = s.substring(index).indexOf("[");
                String number = s.substring(index, index + first_bracket);
                int repeat_times = Integer.parseInt(number);
                String to_return = str_recursed;// + decodeString(s2).repeat(repeat_times);
                index += 1;
                return to_return;
            } else {
                str_recursed = str_recursed + c;
            }
            index++;
        }

        return str_recursed;
    }

    // "hjfh2[b3[a]]poiop[]fsfgs" --> 2[baaa] --> hjfhbaaabaaapoiop
    //                   ^^
    // "hjfhgbaaabaaa"
    // stack = {b3[a], b3[a]}
    // "b"


    /*
     * SOLUTION 1: Pointers, add one every time you hit a number[ --> O(n)
     * SOLUTION 2: Stack???
     * SOLUTION 3: Recursion! --> O(n)?!
     */

    // "hjfh2[b3335[a]]poiop" --> 2[baaa] --> hjfhbaaabaaapoiop
    //        ^
    // "hjfh" + decodeString(").repeat(2)
    //
    // > b3[a]
    //    ^
    //   "b" + decodeString(a).repeat(3)
    //
    // >> a
    //    ^
    //    "a"

    // "hjfh2[b11[a]jk]poiop" --> 2[baaa] --> hjfhbaaaaaaaaaaajkbaaaaaaaaaaajkpoiop
    //      ^
    // "hjfh" + (decodeString("b11[a]jk]poiop") + decodeString()).repeat(2)
    // "hjfh" + "baaaaaaaaaaajkbaaaaaaaaaaajk"  XXXXX
    //
    // > b11[a]jk]poiop
    //    ^
    //   "b" + decodeString("a]jk]poiop").repeat(11)
    //   "b" + "aaaaaaaaaaa"
    //
    // >> a]jk]poiop
    //     ^
    //    "a"

    // "hjfh2[b11[a]jk]poiop" --> 2[baaa] --> hjfhbaaaaaaaaaaajkbaaaaaaaaaaajkpoiop
    //      ^
    // "hjfh" + (decodeString("hjfh2[b11[a]jk]poiop", 6) + decodeString()).repeat(2)
    // "hjfh" + "baaaaaaaaaaajkbaaaaaaaaaaajk"  XXXXX
    //
    // > hjfh2[b11[a]jk]poiop
    //          ^
    //   "b" + decodeString("hjfh2[b11[a]jk]poiop", 10).repeat(11)
    //   "b" + "aaaaaaaaaaa"
    //
    // >> hjfh2[b11[a]jk]poiop
    //               ^
    //    "a"
    //    return "a" (and increment index)
}
