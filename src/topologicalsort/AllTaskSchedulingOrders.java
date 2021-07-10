package topologicalsort;
/*
There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed
before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, write a method to print all
possible ordering of tasks meeting all prerequisites.

Example 1:

Input: Tasks=3, Prerequisites=[0, 1], [1, 2]
Output: [0, 1, 2]
Explanation: There is only possible ordering of the tasks.
Example 2:

Input: Tasks=4, Prerequisites=[3, 2], [3, 0], [2, 0], [2, 1]
Output:
1) [3, 2, 0, 1]
2) [3, 2, 1, 0]
Explanation: There are two possible orderings of the tasks meeting all prerequisites.
Example 3:

Input: Tasks=6, Prerequisites=[2, 5], [0, 5], [0, 4], [1, 4], [3, 2], [1, 3]
Output:
1) [0, 1, 4, 3, 2, 5]
2) [0, 1, 3, 4, 2, 5]
3) [0, 1, 3, 2, 4, 5]
4) [0, 1, 3, 2, 5, 4]
5) [1, 0, 3, 4, 2, 5]
6) [1, 0, 3, 2, 4, 5]
7) [1, 0, 3, 2, 5, 4]
8) [1, 0, 4, 3, 2, 5]
9) [1, 3, 0, 2, 4, 5]
10) [1, 3, 0, 2, 5, 4]
11) [1, 3, 0, 4, 2, 5]
12) [1, 3, 2, 0, 5, 4]
13) [1, 3, 2, 0, 4, 5]




time complexity:  O(V! * E)
 */
import java.util.*;

public class AllTaskSchedulingOrders {
    public static void printOrders(int tasks, int[][] prerequisites) {
        List<Integer> order = new ArrayList<>();
        if (tasks <= 0) return;
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < tasks; i++) {
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        for (int[] task : prerequisites) {
            int parentTask = task[0], childTask = task[1];
            inDegree.put(childTask, inDegree.get(childTask) + 1);
            graph.get(parentTask).add(childTask);
        }
        Queue<Integer> sources = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) sources.add(entry.getKey());
        }
        printAllTopologicalOrder(graph, inDegree, sources, order);
    }

    public static void printAllTopologicalOrder(Map<Integer, List<Integer>> graph,Map<Integer, Integer> inDegree,
                                                Queue<Integer> sources,  List<Integer> sortedOrder){
        if (!sources.isEmpty()) {
            for (Integer vertex : sources) {
                sortedOrder.add(vertex);
                Queue<Integer> sourcesForNextCall = clone(sources);
                // only remove the current source, all other sources should remain in the queue for the next call
                sourcesForNextCall.remove(vertex);
                List<Integer> children = graph.get(vertex); // get the node's children to decrement their in-degrees
                for (int child : children) {
                    inDegree.put(child, inDegree.get(child) - 1);
                    if (inDegree.get(child) == 0)
                        sourcesForNextCall.add(child); // save the new source for the next call
                }

                // recursive call to print other orderings from the remaining (and new) sources
                printAllTopologicalOrder(graph, inDegree, sourcesForNextCall, sortedOrder);

                // backtrack, remove the vertex from the sorted order and put all of its children back to consider
                // the next source instead of the current vertex
                sortedOrder.remove(vertex);
                for (int child : children)
                    inDegree.put(child, inDegree.get(child) + 1);
            }
        }

        // if sortedOrder doesn't contain all tasks, either we've a cyclic dependency between tasks, or
        // we have not processed all the tasks in this recursive call
        if (sortedOrder.size() == inDegree.size())
            System.out.println(sortedOrder);
    }

    public static Queue<Integer> clone(Queue<Integer> sources){
        return new LinkedList<>(sources);
    }

    public static void main(String[] args) {
        AllTaskSchedulingOrders.printOrders(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(4,
                new int[][] { new int[] { 3, 2 }, new int[] { 3, 0 }, new int[] { 2, 0 }, new int[] { 2, 1 } });
        System.out.println();

        AllTaskSchedulingOrders.printOrders(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println();
    }
}
