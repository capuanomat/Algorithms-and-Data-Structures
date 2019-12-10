import java.util.Queue;
import java.util.LinkedList;

class TreeAlgorithms2 {

    static class TreeNode<T> {
        T data;
        TreeNode[] children;

        TreeNode(T data) {
            this.data = data;
            children = null;    // The user must initialize it with desired size.
        }
    }

    static class BinaryNode<T> {
        T data;
        BinaryNode left;
        BinaryNode right;

        BinaryNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static void preOrderTraversal(TreeNode node) {
        // Check that the node is valid
        if (null != node) {

            // Print (or perform whatever operation you want with the node)
            System.out.println(node.data);

            // Iterate through the children (in order since array)
            if (null != node.children) {
                for (TreeNode child : node.children) {
                    preOrderTraversal(child);
                }
            }
        }
    }

    static <T> void binaryLevelOrderTraversal(BinaryNode<T> root) {
        Queue<BinaryNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            BinaryNode<T> curr = queue.remove();

            // Print or do whatever you want with your node
            System.out.println(curr.data);

            if (curr.left != null) {
                queue.add(curr.left);
            }
            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }



    public static void main(String[] args) {

        System.out.println("*** Starting Test 1 ***");
        /* Creating the following test tree:
                             root
                            /    \
                           l      r
                          / \    / \
                        ll  lr  rl  rr
         */
        /*
        TreeNode<String> root = new TreeNode<>("root");

        TreeNode<String> l = new TreeNode<>("l");
        TreeNode<String> r = new TreeNode<>("r");
        root.children = new TreeNode<String>[]{l, r};

        TreeNode<String> ll = new TreeNode<>("ll");
        TreeNode<String> lr = new TreeNode<>("lr");
        ll.children = new TreeNode<String>[]{ll, lr};

        TreeNode<String> rl = new TreeNode<>("rl");
        TreeNode<String> rr = new TreeNode<>("rr");
        rr.children = new TreeNode<String>[]{rl, rr};

        System.out.println("* Setup Complete *");

        preOrderTraversal(root);
        */

        System.out.println("*** Staring Test 2: Level-Order Traversal ***");
        /* Creating the following test tree:
                               1
                            /    \
                           2      3
                          / \    / \
                         4   5  6   7
         */
        BinaryNode<Integer> root = new BinaryNode<>(1);

        BinaryNode<Integer> l = new BinaryNode<>(2);
        BinaryNode<Integer> r = new BinaryNode<>(3);
        root.left = l;
        root.right = r;

        BinaryNode<Integer> ll = new BinaryNode<>(4);
        BinaryNode<Integer> lr = new BinaryNode<>(5);
        l.left = ll;
        l.right = lr;

        BinaryNode<Integer> rl = new BinaryNode<>(6);
        BinaryNode<Integer> rr = new BinaryNode<>(7);
        r.left = rl;
        r.right = rr;

        System.out.println("* Setup Complete *");
        binaryLevelOrderTraversal(root);

    }
}