/*
Given a string and a pattern, find all anagrams of the pattern in the given string.

Every anagram is a permutation of a string. As we know, when we are not allowed to repeat characters while finding
permutations of a string, we get N!N! permutations (or anagrams) of a string having NN characters.
For example, here are the six anagrams of the string “abc”:

abc
acb
bac
bca
cab
cba
Write a function to return a list of starting indices of the anagrams of the pattern in the given string.

Example 1:

Input: String="ppqp", Pattern="pq"
Output: [1, 2]
Explanation: The two anagrams of the pattern in the given string are "pq" and "qp".
Example 2:

Input: String="abbcabc", Pattern="abc"
Output: [2, 3, 4]
Explanation: The three anagrams of the pattern in the given string are "bca", "cab", and "abc".
 */

package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAnagrams {
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();
        Map<Character, Integer> freqMap = new HashMap<>();
        int matches =0, windowStart =0;
        for(char ch : pattern.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        for(int windowEnd =0;windowEnd<str.length();windowEnd++){
            char rightChar = str.charAt(windowEnd);
            if(freqMap.containsKey(rightChar)){
                freqMap.put(rightChar, freqMap.get(rightChar)-1);
                if(freqMap.get(rightChar)==0)matches++;
            }
            if(matches==freqMap.size())resultIndices.add(windowStart);
            if(windowEnd>=pattern.length()-1){
                char leftChar = str.charAt(windowStart);
                if(freqMap.containsKey(leftChar)){
                    if (freqMap.get(leftChar)==0)matches--;
                    freqMap.put(leftChar, freqMap.get(leftChar)+1);
                }
                windowStart++;
            }
        }
        return resultIndices;
    }
    public static void main(String[] args) {
        System.out.println(StringAnagrams.findStringAnagrams("ppqp", "pq"));
        System.out.println(StringAnagrams.findStringAnagrams("abbcabc", "abc"));
    }
}
