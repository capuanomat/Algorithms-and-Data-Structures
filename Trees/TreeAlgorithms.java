package Trees;

/**
 * Created by Matthieu J.B Capuano on 08/06/2021 (my bday :D).
 *
 * This document contains my implementations for some common (and less common)
 * tree algorithms.
 */

import Graphs.GraphAlgorithms.Graph;

/**
 * Tree Traversals:
 * - Pre-order traversal:
 * - In-order traversal:
 * - Post-order traversal:
 * - Level-order traversal:
 *
 * Minimum Spanning Tree:
 * - Prim's Algorithm: Expanding cloud of minimum weighted edges.
 * - Kruskal's Algorithm: Fetch lowest weight edge at every step.
 */
public class TreeAlgorithms {


    public Tree Prims(Graph graph) {
        // TODO
    }

    public Tree Kruskals(Graph graph) {

    }

    public static void main(String[] args) {

    }

    static class Tree {
        TreeNode root;

        Map<TreeNode, Set<TreeNode>> allNodes;

        public Tree(TreeNode root) {
            this.root = root;
            this.allNodes = new HashMap<>();
        }

        public void addNode(TreeNode node) {
            this.allNodes.put(node, new HashSet<>());
        }
    }

    static class TreeNode {
        String id;

        public TreeNode(String id) {
            this.id = id;
        }
    }

    static class TreeEdge {
        TreeNode source;
        TreeNode destination;
        int weight;

        public TreeEdge(TreeNode source, TreeNode dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }
}
