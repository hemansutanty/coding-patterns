package topologicalsort;
/*

Given a directed graph, find the topological ordering of its vertices.

Example 1:

Input: Vertices=4, Edges=[3, 2], [3, 0], [2, 0], [2, 1]
Output: Following are the two valid topological sorts for the given graph:
1) 3, 2, 0, 1
2) 3, 2, 1, 0



Example 2:

Input: Vertices=5, Edges=[4, 2], [4, 3], [2, 0], [2, 1], [3, 1]
Output: Following are all valid topological sorts for the given graph:
1) 4, 2, 3, 0, 1
2) 4, 3, 2, 0, 1
3) 4, 3, 2, 1, 0
4) 4, 2, 3, 1, 0
5) 4, 2, 0, 3, 1

 */

/* Solution steps
a. Initialization
b. Build the graph and find in-degrees of all vertices
c. Find all sources
d. Sort
For each source, do the following things:
Add it to the sorted list.
Get all of its children from the graph.
Decrement the in-degree of each child by 1.
If a child’s in-degree becomes ‘0’, add it to the sources Queue.
Repeat step 1, until the source Queue is empty.

Time and Space Complexity
O(V+E), O(V+E)
 */

import java.util.*;

class TopologicalSort{
    public static List<Integer> sort(int vertices, int[][] edges){
        List<Integer> sortedOrder = new ArrayList<>();
        if(vertices<=0)return  sortedOrder;

        //Initialise and builds the graph
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<vertices;i++){
            inDegree.put(i,0);
            graph.put(i, new ArrayList<>());
        }
        for(int[] edge: edges){
            int parent = edge[0], child = edge[1];
            graph.get(parent).add(child);
            inDegree.put(child, inDegree.get(child)+1);
        }

        // Add all sources(vertex with 0 indegree)
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0)sources.add(entry.getKey());
        }

        //sort using removing sources and their children indegree decrementing
        while(!sources.isEmpty()){
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(int child: children){
                inDegree.put(child, inDegree.get(child)-1);
                if(inDegree.get(child)==0){
                    sources.add(child);
                }
            }
        }
        // topological sort not possible
        if(sortedOrder.size()!=sortedOrder.size()){
            return new ArrayList<>();
        }
        return sortedOrder;
    }
    public static void main(String[] args) {
        List<Integer> result = TopologicalSort.sort(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(5, new int[][] { new int[] { 4, 2 }, new int[] { 4, 3 }, new int[] { 2, 0 },
                new int[] { 2, 1 }, new int[] { 3, 1 } });
        System.out.println(result);

        result = TopologicalSort.sort(7, new int[][] { new int[] { 6, 4 }, new int[] { 6, 2 }, new int[] { 5, 3 },
                new int[] { 5, 4 }, new int[] { 3, 0 }, new int[] { 3, 1 }, new int[] { 3, 2 }, new int[] { 4, 1 } });
        System.out.println(result);
    }
}