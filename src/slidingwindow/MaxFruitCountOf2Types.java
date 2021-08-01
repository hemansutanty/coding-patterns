/*
Given an array of characters where each character represents a fruit tree, you are given two baskets,
and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.

You can start with any tree, but you can’t skip a tree once you have started. You will pick one fruit from each tree
until you cannot, i.e., you will stop when you have to pick from a third fruit type.

Write a function to return the maximum number of fruits in both baskets.

Example 1:

Input: Fruit=['A', 'B', 'C', 'A', 'C']
Output: 3
Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
Example 2:

Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
Output: 5
Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']
 */

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MaxFruitCountOf2Types {
    public static int findLength(char[] arr) {
        if(arr.length<2)return -1;
        Map<Character, Integer> fruitMap = new HashMap<>();
        int windowStart = 0,maxFruits =0;
        for(int windowEnd =0; windowEnd<arr.length;windowEnd++){
            char fruit = arr[windowEnd];
            fruitMap.put(fruit, fruitMap.getOrDefault(fruit,0)+1);
            while(fruitMap.size()>2){
                char leftFruit = arr[windowStart];
                fruitMap.put(leftFruit, fruitMap.get(leftFruit)-1);
                if(fruitMap.get(leftFruit)==0)fruitMap.remove(leftFruit);
                windowStart++;
            }
            maxFruits = windowEnd-windowStart+1;
        }
        return maxFruits;
    }
    public static void main(String[] args) {
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " +
                MaxFruitCountOf2Types.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }
}
