/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }

  private class TreeNode {
    TreeNode left;
    Treenode right;
    int val;

  }


  public int largestNonAdjacent(TreeNode root) {
    // DP? -> recursive?
    // --> Proof of correctness?

    // tn(keep) = max(tl(false) + tr(false) + curr, 0 + 0 + 6 = 6 -- 7 -- 5 + 100 + 5 = 110
    //                tl(true) + tr(true), 3 + 2 = 5 -- 100 -- 6 + 100 = 106
    //                tl(true) + tr(false),
    //                tl(false) + tl(true)) 2 + 3 = 5 -- 100 -- 7 + 6 = 13

    maxes tl = recurse(root.left);
    maxes tr = recurse(root.right);

    return Math.max(tl.drop + tr.drop + root.val,
                    tl.keep + tr.keep,
                    tl.keep + tr.drop,
                    tl.keep + tl.drop);

    // Other (bad options):
    // Graph search
    // Tree travesals: pre/in/post orders & level order
  }

  public maxes recurse(TreeNode root) {
    if (root == null)
      return new maxes(0, 0);

    maxes tl = recurse(root.left);
    maxes tr = recurse(root.right);

    int val1 = tl.drop + tr.drop + root.val;
    int val2 = tl.keep + tr.keep;
    int val3 = tl.keep + tr.drop;
    int val4 = tr.keep + tl.drop;
    int max = Math.max(val2, val3, val4, val5);

    maxes curr = new maxes(val1, Math.max(val2, val3, val4));

    return curr;
  }

  private class maxes {
    int keepRoot;
    int dropRoot;

    // int tltrue;
    // int tlfalse;
    // int trtrue;
    // int trfalse;

    public maxes(int keep, int drop) {
      this.keepRoot = keepRoot;
      this.dropRoot = dropRoot;
  }
}

/*
-> could be anything
-> Non-negative
*/


/*
//    5
//   /\
    6  7
   / \  \
  3  2  100

1. non-adjacent
2. sum is the largest
3. return the sum
*/
