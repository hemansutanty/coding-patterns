package topologicalsort;
/*
We are given an undirected graph that has characteristics of a k-ary tree. In such a graph, we can choose any node as
the root to make a k-ary tree. The root (or the tree) with the minimum height will be called Minimum Height Tree (MHT).
There can be multiple MHTs for a graph. In this problem, we need to find all those roots which give us MHTs.
Write a method to find all MHTs of the given graph and return a list of their roots.

Example 1:

Input: vertices: 5, Edges: [[0, 1], [1, 2], [1, 3], [2, 4]]
Output:[1, 2]
Explanation: Choosing '1' or '2' as roots give us MHTs. In the below diagram, we can see that the
height of the trees with roots '1' or '2' is three which is minimum.

Input: vertices: 4, Edges: [[0, 1], [0, 2], [2, 3]]
Output:[0, 2]
Explanation: Choosing '0' or '2' as roots give us MHTs. In the below diagram, we can see that the
height of the trees with roots '0' or '2' is three which is minimum.
 */
import java.util.*;

public class MinimumHeightTrees {
    public static List<Integer> findTrees(int nodes, int[][] edges) {
        List<Integer> mht = new ArrayList<>();
        if(nodes<=0)return mht;
        if(nodes==1){
            mht.add(0);return mht;
        }

        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<nodes;i++){
            indegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }

        for(int[] edge : edges){
            int node1 = edge[0], node2=edge[1];
            indegree.put(node1, indegree.get(node1)+1);
            indegree.put(node2, indegree.get(node2)+1);
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        Queue<Integer> leaves = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: indegree.entrySet()){
            if(entry.getValue()==1)leaves.add(entry.getKey());
        }

        int totalNodes = nodes;
        while (totalNodes > 2) {
            int leavesSize = leaves.size();
            totalNodes -= leavesSize;
            for (int i = 0; i < leavesSize; i++) {
                int vertex = leaves.poll();
                List<Integer> children = graph.get(vertex);
                for (int child : children) {
                    indegree.put(child, indegree.get(child) - 1);
                    if (indegree.get(child) == 1) // if the child has become a leaf
                        leaves.add(child);
                }
            }
        }
        mht.addAll(leaves);
        return mht;
    }

    public static void main(String[] args) {
        List<Integer> result = MinimumHeightTrees.findTrees(5,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 }, new int[] { 2, 4 } });
        System.out.println("Roots of MHTs: " + result);

        result = MinimumHeightTrees.findTrees(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 0, 2 }, new int[] { 2, 3 } });
        System.out.println("Roots of MHTs: " + result);

        result = MinimumHeightTrees.findTrees(4,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 1, 3 } });
        System.out.println("Roots of MHTs: " + result);
    }
}
