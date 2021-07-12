package topk;

/*
You are given a list of tasks that need to be run, in any order, on a server.
Each task will take one CPU interval to execute but once a task has finished,
it has a cooling period during which it can’t be run again.
If the cooling period for all tasks is ‘K’ intervals,
find the minimum number of CPU intervals that the server needs to finish all tasks.

If at any time the server can’t execute any task then it must stay idle.

Example 1:

Input: [a, a, a, b, c, c], K=2
Output: 7
Explanation: a -> c -> b -> a -> c -> idle -> a
Example 2:

Input: [a, b, a], K=3
Output: 5
Explanation: a -> b -> idle -> idle -> a

Time : O(nlogn)
 */


import java.util.*;

public class TaskScheduler {
    public static int scheduleTasks(char[] tasks, int k) {
        int intervalCount  = 0;
        Map<Character, Integer> tasksFreq = new HashMap<>();
        for(char task: tasks){
            tasksFreq.put(task, tasksFreq.getOrDefault(task, 0)+1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((p1, p2)->p2.getValue()-p1.getValue());
        maxHeap.addAll(tasksFreq.entrySet());
        while(!maxHeap.isEmpty()){
            List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();
            int n = k+1;
            for(;n>0&&!maxHeap.isEmpty();n--){
                intervalCount++;
                Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
                if(currentEntry.getValue()>1){
                    currentEntry.setValue(currentEntry.getValue()-1);
                    waitList.add(currentEntry);
                }
            }
            maxHeap.addAll(waitList);
            if(!maxHeap.isEmpty()){
                intervalCount+=n;
            }
        }
        return intervalCount;
    }

    public static void main(String[] args) {
        char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));

        tasks = new char[] { 'a', 'b', 'a' };
        System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
    }
}
