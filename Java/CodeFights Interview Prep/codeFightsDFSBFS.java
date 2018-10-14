package CodeFightsInterview;

import java.util.*;

/**
 * Created by Matthieu J.B Capuano on 11/15/2017.
 */
public class codeFightsDFSBFS {
    /* =============================== DFS & BFS =============================== */
    class Tree<T> {
        Tree(T x) {
            value = x;
        }
        T value;
        Tree<T> left;
        Tree<T> right;
    }

    /**
     * PROBLEM: traverseTree
     * ........
     * .......
     * @param t
     * @return
     */
    int[] traverseTree(Tree<Integer> t) {
        if (t == null) return new int[0];
        Queue<Tree<Integer>> currList = new LinkedList();
        ArrayList<Integer> toReturn = new ArrayList();
        currList.add(t);
        while (!currList.isEmpty()) {
            Tree<Integer> curr = currList.remove();
            if (curr != null) {
                toReturn.add(curr.value);
                currList.add(curr.left);
                currList.add(curr.right);
            }
        }
        int[] toReturn2 = new int[toReturn.size()];
        for (int i = 0; i < toReturn.size(); i++) {
            toReturn2[i] = toReturn.get(i);
        }
        return toReturn2;
    }
}
