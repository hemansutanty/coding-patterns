package topk;

/*
Given an unsorted array of numbers, find the top ‘K’ frequently occurring numbers in it.

Example 1:

Input: [1, 3, 5, 12, 11, 12, 11], K = 2
Output: [12, 11]
Explanation: Both '11' and '12' apeared twice.
Example 2:

Input: [5, 12, 11, 3, 11], K = 2
Output: [11, 5] or [11, 12] or [11, 3]
Explanation: Only '11' appeared twice, all other numbers appeared once.

Time: O(N+N*logK)
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentNumbers {
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length+1];
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int num: nums){
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }
        for(Map.Entry<Integer, Integer> entry: countMap.entrySet()){
            int count = entry.getValue(); int key = entry.getKey();
            if(bucket[count]==null){
                bucket[count]=new ArrayList<>();
            }
            bucket[count].add(key);
        }
        List<Integer> result = new ArrayList<>();
        int j=0;
        for(int i=bucket.length-1;i>=0 && j<k;i--){
            if(bucket[i]!=null){
                for(int num: bucket[i]){
                    System.out.println(j+"\t"+num+"\t"+k);
                    result.add(num);
                    j++;
                    if (result.size()==k)return result;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 1, 3, 5, 12, 11, 12, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);

        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] { 5, 12, 11, 3, 11 }, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}
