package CodeFightsInterview;

import java.util.ArrayList;

/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 */
public class codeFightsTreesBasic {
    /* =============================== TREES: BASIC =============================== */

    class Tree<T> {
        Tree(T x) {
            value = x;
        }
        T value;
        Tree<T> left;
        Tree<T> right;
    }

    /**
     * PROBLEM: hasPathWithGivenSum
     *
     * NOTE: None
     *
     * DESCRIPTION: Given a binary tree t and an integer s, determine whether there is a root to
     *              leaf path in t such that the sum of vertex values equals s.
     *
     * EXAMPLES: For ...
     *
     *
     * INPUT/OUTPUT:
     *
     */

    /**
     * MY SOLUTION
     */
    boolean hasPathWithGivenSum(Tree<Integer> t, int s) {
        if (t == null) {
            return (s == 0) ? true : false;
        }

        return hasSum(t, s, 0);
    }

    boolean hasSum(Tree<Integer> t, int s, int sum) {
        if (t == null) {
            return false;
        }
        sum += t.value;
        if ((t.left == null) && (t.right == null)) {
            return sum == s;
        }

        return (hasSum(t.left, s, sum) || hasSum(t.right, s, sum));
    }



    /**
     * PROBLEM: isTreeSymmetric
     *
     * DESCRIPTION: Given a binary tree t, determine whether it is symmetric around its center,
     *              i.e. each side mirrors the other.
     *
     * EXAMPLES:
     *
     * INPUT/OUTPUT:
     */

    /* MY SOLUTION */
    boolean isTreeSymmetric(Tree<Integer> t) {
        if (t == null) { return true; }

        return helper(t.left, t.right);
    }

    boolean helper(Tree<Integer> t1, Tree<Integer> t2) {
        if ((t1 == null) && (t2 == null)) return true;
        if ((t1 == null) || (t2 == null)) return false;
        if (!(t1.value.equals(t2.value))) return false;

        return (helper(t1.left, t2.right) && helper(t1.right, t2.left));
    }



    /**
     * PROBLEM: kthSmallestInBST
     * NOTE: O(1) space
     * DESCRIPTION: ... Given a binary search tree t, find the kth smallest element in it.
     *              Note that kth smallest element means kth element in increasing order.
     *              See examples for better understanding.
     * @param t
     * @param k
     * @return
     * SOLUTION 1: NOT O(1) space
     */
    int kthSmallestInBST(Tree<Integer> t, int k) {
        ArrayList<Integer> list = new ArrayList<>();

        inorder(t, list);
        return list.get(k - 1);
    }

    void inorder(Tree<Integer> curr, ArrayList<Integer> list) {
        if (null != curr) {
            inorder(curr.left, list);
            list.add(curr.value);
            inorder(curr.right, list);
        }
    }

    /* Solution 2 */
    int k, toRet;
    int kthSmallestInBST2(Tree<Integer> t, int k) {
        this.k = k - 1;

        inorder(t);
        return toRet;
    }
    void inorder(Tree<Integer> curr) {
        if (null != curr) {
            inorder(curr.left);
            if (0 == k) toRet = curr.value;
            k--;
            inorder(curr.right);
        }
    }




    /**
     * PROBLEM: isSubtree
     * DESCRIPTION: Given two binary trees t1 and t2, determine whether the second tree is a
     *              subtree of the first tree. A subtree for vertex v in a binary tree t is a tree
     *              consisting of v and all its descendants in t. Determine whether or not there
     *              is a vertex v (possibly none) in tree t1 such that a subtree for vertex v
     *              (possibly empty) in t1 equals t2.
     *
     * @param t1
     * @param t2
     * @return
     *
     * SOLUTION 1
     */
    boolean isSubtree(Tree<Integer> t1, Tree<Integer> t2) {
        if (null == t1) return (null == t2);
        if (null == t2) return true;

        return findSame(t1, t2);
    }

    boolean findSame(Tree<Integer> s1, Tree<Integer> s2) {
        if (null != s1) {
            if (s1.value.equals(s2.value)) {
                if (treeComp(s1, s2)) return true;
            }
            return (findSame(s1.left, s2) || findSame(s1.right, s2));
        }
        return false;
    }

    boolean treeComp(Tree<Integer> c1, Tree<Integer> c2) {
        if ((null != c1) && (null != c2)) {
            if (!c1.value.equals(c2.value)) return false;
            return (treeComp(c1.left, c2.left) &&
                    treeComp(c1.right, c2.right));
        } else if ((null == c1) && (null == c2)) {
            return true;
        } else {
            return false;
        }
    }

    /* SOLUTION 2 */
    boolean isSubtree2(Tree<Integer> t1, Tree<Integer> t2) {
        return treeString(t1).contains(treeString(t2));
    }

    String treeString(Tree<Integer> t){
        if(t == null) return "   ";
        return t.value+" "+treeString(t.left)+" "+treeString(t.right);
    }

    /* Solution 3 */
    boolean isSubtree3(Tree<Integer> t1, Tree<Integer> t2) {
        if (t2 == null)
            return true;

        if (t1 == null)
            return false;

        if (areIdentical(t1, t2))
            return true;

        return isSubtree(t1.left, t2)
                || isSubtree(t1.right, t2);
    }

    boolean areIdentical(Tree<Integer> root1, Tree<Integer> root2) {
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null)
            return false;
        return (root1.value.equals(root2.value)
                && areIdentical(root1.left, root2.left)
                && areIdentical(root1.right, root2.right));
    }
}
