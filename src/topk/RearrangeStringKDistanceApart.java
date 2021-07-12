package topk;

/*
Given a string and a number ‘K’, find if the string can be rearranged such that the same characters are at
least ‘K’ distance apart from each other.

Example 1:

Input: "mmpp", K=2
Output: "mpmp" or "pmpm"
Explanation: All same characters are 2 distance apart.
Example 2:

Input: "Programming", K=3
Output: "rgmPrgmiano" or "gmringmrPoa" or "gmrPagimnor" and a few more
Explanation: All same characters are 3 distance apart.
Example 3:

Input: "aab", K=2
Output: "aba"
Explanation: All same characters are 2 distance apart.
Example 4:

Input: "aappa", K=3
Output: ""
Explanation: We cannot find an arrangement of the string where any two 'a' are 3 distance apart.


time : O(n*logn)
 */
import java.util.*;

public class RearrangeStringKDistanceApart {
    public static String reorganizeString(String str, int k) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch : str.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((p1,p2)->p2.getValue()-p1.getValue());
        maxHeap.addAll(freqMap.entrySet());
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            result.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue()-1);
            queue.offer(currentEntry);
            if(queue.size()==k){
                Map.Entry<Character, Integer> entry = queue.poll();
                if(entry.getValue()>0){
                    maxHeap.add(entry);
                }
            }
        }
        return result.length()==str.length()?result.toString():"";
    }

    public static void main(String[] args) {
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("mmpp", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("Programming", 3));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aab", 2));
        System.out.println("Reorganized string: " +
                RearrangeStringKDistanceApart.reorganizeString("aappa", 3));
    }
}
