package topologicalsort;

import java.sql.Array;
import java.util.*;

/*
There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed
before it can be scheduled. Given the number of tasks and a list of prerequisite pairs,
find out if it is possible to schedule all the tasks.

Example 1:

Input: Tasks=3, Prerequisites=[0, 1], [1, 2]
Output: true
Explanation: To execute task '1', task '0' needs to finish first. Similarly, task '1' needs to finish
before '2' can be scheduled. A possible sceduling of tasks is: [0, 1, 2]
Example 2:

Input: Tasks=3, Prerequisites=[0, 1], [1, 2], [2, 0]
Output: false
Explanation: The tasks have cyclic dependency, therefore they cannot be sceduled.
Example 3:

Input: Tasks=6, Prerequisites=[2, 5], [0, 5], [0, 4], [1, 4], [3, 2], [1, 3]
Output: true
Explanation: A possible scheduling of tasks is: [0 1 4 3 2 5]
 */

/*
Simple Topological sorting
time: O(V+E)
space: O(V+E)

 */
public class TaskScheduling {
    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites){
        List<Integer> ordering = new ArrayList<>();
        if(tasks<=0) return false;

        HashMap<Integer, Integer> inDegree = new HashMap<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<tasks;i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        for(int[] task: prerequisites){
            int parentTask =task[0], childTask = task[1];
            inDegree.put(childTask, inDegree.get(childTask)+1);
            graph.get(parentTask).add(childTask);
        }

        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0){
                sources.add(entry.getKey());
            }
        }
        while(!sources.isEmpty()){
            int vertex = sources.poll();
            ordering.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(int child: children){
                inDegree.put(child, inDegree.get(child)-1);
                if(inDegree.get(child)==0)sources.add(child);
            }
        }
        return ordering.size()==tasks;
    }
    public static void main(String[] args) {

        boolean result = TaskScheduling.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
                new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }
}
