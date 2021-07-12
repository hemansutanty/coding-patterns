package topk;

/*
Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.

Example 1:

Input: "aappp"
Output: "papap"
Explanation: In "papap", none of the repeating characters come next to each other.
Example 2:

Input: "Programming"
Output: "rgmrgmPiano" or "gmringmrPoa" or "gmrPagimnor", etc.
Explanation: None of the repeating characters come next to each other.
Example 3:

Input: "aapa"
Output: ""
Explanation: In all arrangements of "aapa", atleast two 'a' will come together e.g., "apaa", "paaa".

time: O(N*logN)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RearrangeString {
    public static String rearrangeString(String str) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: str.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((p1,p2)->p2.getValue()-p1.getValue());
        maxHeap.addAll(freqMap.entrySet());
        Map.Entry<Character, Integer> previousEntry=null;
        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()){
            Map.Entry<Character, Integer> currentEntry = maxHeap.poll();
            if(previousEntry!=null && previousEntry.getValue()>0){
                maxHeap.add(previousEntry);
            }
            result.append(currentEntry.getKey());
            currentEntry.setValue(currentEntry.getValue()-1);
            previousEntry=currentEntry;
        }
        return result.length()==str.length()?result.toString():"";
    }

    public static void main(String[] args) {
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aappp"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("Programming"));
        System.out.println("Rearranged string: " + RearrangeString.rearrangeString("aapa"));
    }
}
