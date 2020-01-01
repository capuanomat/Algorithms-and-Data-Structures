import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LeetCode {

    public static void main(String[] args) {
        LeetCode leet = new LeetCode();

        // --- PROBLEM 315 --- //
        int[] nums0 = new int[0];
        int[] nums1 = {5, 2, 6, 1};
        int[] nums2 = {7, 32, 89, 4, 9, 3, 78, 2, 78, 3, 6, 89};
        int[] nums3 = {1, 1, 1, 1, 1, 1};

        int[] counted0 = leet.countSmaller(nums0);
        int[] counted1 = leet.countSmaller(nums1);
        int[] counted2 = leet.countSmaller(nums2);
        int[] counted3 = leet.countSmaller(nums3);

        System.out.println("nums0: " + Arrays.toString(nums0) + "-- counted0: " + Arrays.toString(counted0));
        System.out.println("nums1: " + Arrays.toString(nums1) + "-- counted1: " + Arrays.toString(counted1));
        System.out.println("nums2: " + Arrays.toString(nums2) + "-- counted2: " + Arrays.toString(counted2));
        System.out.println("nums3: " + Arrays.toString(nums3) + "-- counted3: " + Arrays.toString(counted3));


        // --- PROBLEM 968 --- //
        TreeNode root1 = new TreeNode(0);
        root1.left = new TreeNode(0);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(0);
        System.out.println(leet.minCameraCover(root1));

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.left.right = new TreeNode(5);
        System.out.println(leet.minCameraCover(root2));

        TreeNode root3 = new TreeNode(5);
        root3.right = new TreeNode(4);
        root3.right.right = new TreeNode(3);
        root3.right.right.left = new TreeNode(1);
        root3.right.right.left.left = new TreeNode(0);
        root3.right.right.right = new TreeNode(2);
        root3.right.right.right.left = new TreeNode(1);
        root3.right.right.right.right = new TreeNode(1);
        root3.right.right.right.left.right = new TreeNode(0);
        root3.right.right.right.right.right = new TreeNode(0);
        System.out.println(leet.minCameraCover(root3));
    }


    /* PROBLEM 315
     * TIME EFFICIENCY:   9ms, faster than 58.09% of Java online submissions
     * SPACE EFFICIENCY: 40.6MB, less than 50.00% of Java online submissions
     * DESCRIPTION: You are given an integer array nums and you have to return a new counts array. The counts array has the property
     *              where counts[i] is the number of smaller elements to the right of nums[i].
     *
     * EXAMPLE:
     *          Input:  [5,2,6,1]
     *          Output: [2,1,1,0]
     *          Explanation:
     *              To the right of 5 there are 2 smaller elements (2 and 1).
     *              To the right of 2 there is only 1 smaller element (1).
     *              To the right of 6 there is 1 smaller element (1).
     *              To the right of 1 there is 0 smaller element.
     */
    /* SOLUTION: The idea of this solution is that we apply merge sort and count as we do so
        [1, 5, 6, 9] [1, 2, 4, 9]
                  ^         ^
       count = 2
     */
    public class node {
        int value;
        int index;
        int num_smaller;

        node(int value, int index, int num_smaller) {
            this.value = value;
            this.index = index;
            this.num_smaller = num_smaller;
        }
    }

    public int[] countSmaller(int[] nums) {

        // Pre-Processing: Create new array of nodes to keep track of original index, and # of smaller elements
        node[] nums_nodes = new node[nums.length];
        for (int i = 0; i < nums.length; i++) {
            nums_nodes[i] = new node(nums[i], i, 0);
        }

        // Sort and count the number of smaller elements
        node[] sortedAndCounted = mergeSortCount(nums_nodes);

        // Post-Processing:
        int[] num_smaller_arr = new int[nums.length];
        for (node entry : sortedAndCounted) {
            num_smaller_arr[entry.index] = entry.num_smaller;
        }

        return num_smaller_arr;
    }

    public node[] mergeSortCount(node[] nums_nodes) {

        if (nums_nodes.length <= 1) {
            return nums_nodes;
        }

        int mid = nums_nodes.length / 2;

        node[] L = new node[mid];   // Length of half of input array
        node[] R = new node[nums_nodes.length - mid];

        int ind = 0, l = 0, r = 0;
        while (ind < nums_nodes.length) {
            if (ind < mid) {
                L[l++] = nums_nodes[ind];
            } else {
                R[r++] = nums_nodes[ind];
            }
            ind++;
        }

        // Merge sort and countthe left and right arrays
        node[] L_sorted = mergeSortCount(L);
        node[] R_sorted = mergeSortCount(R);

        // Merge the arrays
        int count_smaller = 0;
        int l_pointer = 0;
        int r_pointer = 0;
        int main_index = 0;

        node[] to_return = new node[L_sorted.length + R_sorted.length];
        while ((l_pointer < L_sorted.length) && (r_pointer < R_sorted.length)) {
            if (L_sorted[l_pointer].value <= R_sorted[r_pointer].value) {
                //  [2, 4, 7, 9, 11, 15], [3, 5, 7, 10, 13, 18]
                //   ^                     ^
                L_sorted[l_pointer].num_smaller += count_smaller;
                to_return[main_index] = L_sorted[l_pointer++];
            } else {
                //  [2, 4, 7, 9, 11, 15], [3, 5, 7, 10, 13, 18]
                //                            ^
                count_smaller++;
                to_return[main_index] = R_sorted[r_pointer++];
            }
            main_index++;
        }

        while (l_pointer < L_sorted.length) {
            L_sorted[l_pointer].num_smaller += count_smaller;
            to_return[main_index++] = L_sorted[l_pointer++];
        }

        while (r_pointer < R_sorted.length) {
            to_return[main_index++] = R_sorted[r_pointer++];
        }

        return to_return;
    }


    /** PROBLEM 968: https://leetcode.com/problems/binary-tree-cameras/
     *  DESCRIPTION: Given a binary tree, we install cameras on the nodes of the tree.
     *               Each camera at a node can monitor its parent, itself, and its immediate children.
     *               Calculate the minimum number of cameras needed to monitor all nodes of the tree.
     *
     *  EXAMPLES:
     *  ...
     * @param root
     * @return
     */
    public static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

    int count;
    public int minCameraCover(TreeNode root) {
        /*
        Initial Thoughts:
        -> "Minimum", DP?
        -> Tree Traversals -> Level order traversal (BFS) -> Minimum?

            .5
             \
              .4
               \
                .3
               /  \
              .1   .2
             /   /  \
            .0   .1    .1
                 \    \
                  .0    .0

         DP: vi =  {1 + v_{all the ones below}, 0 + v_{all the ones below}}

         1. Recursively go to the leaves, don't put camera at the leaves
         2. Put camera at parents of leaf
         3. Then place cameras every other level if neither of the children are covered

         Level 0: No need
         Level 1: Need
         Level 2: No need
         Level 3: Need if children are level (2 & 2) || (2 & 0)
         Level 4: Need if children are level (3^ & 0) || (3^ & 2)
         Level 5: Need if children are level (4^ & 0) || (4^ & 2) || (4^ & 3^)
        */
        count = 0;
        dfs(root);
        return count;
    }

    public boolean dfs(TreeNode node) {
        if ((null == node.left) && (null == node.right)) {
            System.out.println("False : " + node.val);
            return false;
        }

        // If there is a left or right node, call this recursively on it
        boolean l = false, r = false;
        if (null != node.left) {
            l = dfs(node.left);
        }
        if (null != node.right) {
            r = dfs(node.right);
        }

        if (!l && !r) {
            count++;
            System.out.println("True : " + node.val);
            return true;
        }

        // One of the children is covered so this node does not need to be covered
        System.out.println("False : " + node.val);
        return false;
    }
}