package topk;

/*
Given a string, sort it based on the decreasing frequency of its characters.

Example 1:

Input: "Programming"
Output: "rrggmmPiano"
Explanation: 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.
Example 2:

Input: "abcbab"
Output: "bbbaac"
Explanation: 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.

Time : O(N*logN)
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FrequencySort {
    public static String sortCharacterByFrequency(String str) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: str.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((p1,p2)->p2.getValue()-p1.getValue());
        StringBuilder resultStr = new StringBuilder();

        maxHeap.addAll(freqMap.entrySet());

        while(maxHeap.size()>0){
            Map.Entry<Character,Integer> entry = maxHeap.poll();
            for(int i=0;i<entry.getValue();i++){
                resultStr.append(entry.getKey());
            }
        }
        return resultStr.toString();
    }

    public static void main(String[] args) {
        String result = FrequencySort.sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = FrequencySort.sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);
    }
}
