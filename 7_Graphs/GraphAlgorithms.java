/**
 * Created by Matthieu J.B Capuano on 11/27/2017.
 *
 * This document contains my implementations for some common (and less common)
 * graph algorithms.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.List;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

class GraphAlgorithms {
    /*
            DFS (Stack): ...

            BFS (Queue): Fewest # of edges path from one vertex to another
                         Also shortest path if all edges have same weight

            Uniform Cost Search (Priority Queue): Shortest path from one vertex to another

            Dijkstra's (Priority Queue): Shortest path from one vertex to every other vertex

            Bellman-Ford (...): Shortest path from one vertex to ever other vertex
                                           Works with negative edge weights

            Floyd-Warshall (...): Shortest path between every pair of vertices
                                  Works with negative edge weights

            A*: Uniform Cost Search with a heuristic

            Hybrid A*:
            https://blog.habrador.com/2015/11/explaining-hybrid-star-pathfinding.html

            Theta*:
            https://en.wikipedia.org/wiki/Theta*

            Other Path Plannig:
            https://www.sciencedirect.com/science/article/pii/S2214914718300333
            https://www.cc.gatech.edu/~dellaert/pub/Ok13icra.pdf
            http://www.cs.cmu.edu/~jgonzale/Thesis_Proposal/Thesis_proposal_v2.09.pdf
     */


     public static void BFS(Graph graph) {
         System.out.println("= STARTING BFS =");
         Queue<GraphNode> discovered = new LinkedList<>();
         Set<GraphNode> discoveredSet = new HashSet<>();
         Set<GraphNode> expanded = new HashSet<>();

         discovered.add(graph.root);
         discoveredSet.add(graph.root);

         while (!discovered.isEmpty()) {
             // Take the latest entry in the Queue
             GraphNode curr = discovered.remove();
             discoveredSet.remove(curr);
             expanded.add(curr);
             System.out.println("Expanding: " + curr.id);

             Set<GraphEdge> currEdges = graph.allNodes.get(curr);
             for (GraphEdge edge: currEdges) {
                 GraphNode neighbor = edge.dest;
                 if (!discoveredSet.contains(neighbor) && !expanded.contains(neighbor)) {
                     discovered.add(neighbor);
                     discoveredSet.add(neighbor);
                 }
             }
         }
     }




    public static void DFS(Graph graph) {
        System.out.println("= STARTING DFS =");
        Stack<GraphNode> discovered = new Stack<>();
        Set<GraphNode> discoveredSet = new HashSet<>();
        Set<GraphNode> explored = new HashSet<>();

        discovered.push(graph.root);
        discoveredSet.add(graph.root);

        while (!discovered.isEmpty()) {
            GraphNode curr = discovered.pop();
            discoveredSet.remove(curr);
            explored.add(curr);
            System.out.println("Expanding: " + curr.id);

            Set<GraphEdge> currEdges = graph.allNodes.get(curr);
            for(GraphEdge edge: currEdges) {
                GraphNode neighbor = edge.dest;
                if (!discoveredSet.contains(neighbor) && !explored.contains(neighbor)) {
                    discovered.add(neighbor);
                    discoveredSet.add(neighbor);
                }
            }
        }
     }





     private static Graph makeTestGraph1() {
         Graph graph = new Graph();

         // Creating the nodes
         GraphNode A = new GraphNode("A");
         GraphNode B = new GraphNode("B");
         GraphNode C = new GraphNode("C");
         GraphNode D = new GraphNode("D");
         GraphNode E = new GraphNode("E");
         GraphNode F = new GraphNode("F");
         GraphNode G = new GraphNode("G");
         GraphNode H = new GraphNode("H");
         GraphNode I = new GraphNode("I");
         GraphNode J = new GraphNode("J");

         // Creating the edges
         Set<GraphEdge> AEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(A, B),
            new GraphEdge(A, C),
            new GraphEdge(A, D),
            new GraphEdge(A, E)
         ));
         Set<GraphEdge> BEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(B, A)
         ));
         Set<GraphEdge> CEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(C, A),
            new GraphEdge(C, F)
         ));
         Set<GraphEdge> DEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(D, I),
            new GraphEdge(D, A)
         ));
         Set<GraphEdge> EEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(E, J),
            new GraphEdge(E, A)
         ));
         Set<GraphEdge> FEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(F, C),
            new GraphEdge(F, G),
            new GraphEdge(F, H)
         ));
         Set<GraphEdge> GEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(G, F)
         ));
         Set<GraphEdge> HEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(H, F)
         ));
         Set<GraphEdge> IEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(I, J),
            new GraphEdge(I, D)
         ));
         Set<GraphEdge> JEdges = new HashSet<>(Arrays.asList(
            new GraphEdge(J, I),
            new GraphEdge(J, E)
         ));

         graph.root = A;
         graph.allNodes.put(A, AEdges);
         graph.allNodes.put(B, BEdges);
         graph.allNodes.put(C, CEdges);
         graph.allNodes.put(D, DEdges);
         graph.allNodes.put(E, EEdges);
         graph.allNodes.put(F, FEdges);
         graph.allNodes.put(G, GEdges);
         graph.allNodes.put(H, HEdges);
         graph.allNodes.put(I, IEdges);
         graph.allNodes.put(J, JEdges);

         return graph;
     }

     public static void main(String[] args) {
         System.out.println("===== Creating TestGraph1 =====");
         Graph testGraph1 = makeTestGraph1();

         System.out.println("=== Running BFS on TestGraph1 ===");
         BFS(testGraph1);

         System.out.println("=== Running DFS on TestGraph1 ===");
         DFS(testGraph1);
     }


     static class SearchState {
         GraphNode curr;
         GraphNode parent;
         // List<GraphNode> parents;
     }

     static class GraphNode {
         String id;

         public GraphNode(String id) {
             this.id = id;
         }
     }

     static class Graph {
         GraphNode root;

         Map<GraphNode, Set<GraphEdge>> allNodes;

         public Graph() {
             this.allNodes = new HashMap<>();
         }

         private GraphNode getRoot() {
             return this.root;
         }

         private void addNode(GraphNode node) {
             this.allNodes.put(node, new HashSet<GraphEdge>());
         }
     }

     static class GraphEdge {
         GraphNode source;
         GraphNode dest;

         int weight;

         public GraphEdge(GraphNode source, GraphNode dest) {
             this.source = source;
             this.dest = dest;
         }

         private int getWeight() {
             return weight;
         }

         private void setWeight() {
             this.weight = weight;
         }
     }

}
