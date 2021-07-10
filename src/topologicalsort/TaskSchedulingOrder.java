package topologicalsort;

/*
There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed
before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, write a method to find the
ordering of tasks we should pick to finish all tasks.

Example 1:

Input: Tasks=3, Prerequisites=[0, 1], [1, 2]
Output: [0, 1, 2]
Explanation: To execute task '1', task '0' needs to finish first. Similarly, task '1' needs to finish
before '2' can be scheduled. A possible scheduling of tasks is: [0, 1, 2]
Example 2:

Input: Tasks=3, Prerequisites=[0, 1], [1, 2], [2, 0]
Output: []
Explanation: The tasks have cyclic dependency, therefore they cannot be scheduled.
Example 3:

Input: Tasks=6, Prerequisites=[2, 5], [0, 5], [0, 4], [1, 4], [3, 2], [1, 3]
Output: [0 1 4 3 2 5]
Explanation: A possible scheduling of tasks is: [0 1 4 3 2 5]
 */

/*
Similar Problems:
Course Schedule: There are ‘N’ courses, labeled from ‘0’ to ‘N-1’. Each course has some prerequisite courses which need
to be completed before it can be taken. Given the number of courses and a list of prerequisite pairs, write a method to
find the best ordering of the courses that a student can take in order to finish all courses.


 */

import java.util.*;

public class TaskSchedulingOrder {
    public static List<Integer> findOrder(int tasks, int[][] prerequisites){
        List<Integer> sortedOrder = new ArrayList<>();
        if(tasks<=0) return sortedOrder;

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i=0;i<tasks;i++){
            inDegree.put(i, 0);
            graph.put(i, new ArrayList<>());
        }
        for(int[] task:prerequisites){
            int parentTask = task[0], childTask = task[1];
            inDegree.put(childTask, inDegree.get(childTask)+1);
            graph.get(parentTask).add(childTask);
        }
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0)sources.add(entry.getKey());
        }
        while(!sources.isEmpty()){
            int vertex = sources.poll();
            sortedOrder.add(vertex);
            List<Integer> children = graph.get(vertex);
            for(int child: children){
                inDegree.put(child, inDegree.get(child)-1);
                if(inDegree.get(child)==0)sources.add(child);
            }
        }
        if(sortedOrder.size()!=tasks)return  new ArrayList<>();
        return sortedOrder;
    }
    public static void main(String[] args) {
        List<Integer> result = TaskSchedulingOrder.findOrder(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println(result);

        result = TaskSchedulingOrder.findOrder(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println(result);

        result = TaskSchedulingOrder.findOrder(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 }, new int[] { 0, 4 },
                new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println(result);
    }
}
