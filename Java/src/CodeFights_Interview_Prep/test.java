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
    }
}