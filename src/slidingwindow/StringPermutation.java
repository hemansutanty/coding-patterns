/*
Given a string and a pattern, find out if the string contains any permutation of the pattern.

Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:

abc
acb
bac
bca
cab
cba
If a string has ‘n’ distinct characters, it will have n!n! permutations.

Example 1:

Input: String="oidbcaf", Pattern="abc"
Output: true
Explanation: The string contains "bca" which is a permutation of the given pattern.
Example 2:

Input: String="odicf", Pattern="dc"
Output: false
Explanation: No permutation of the pattern is present in the given string as a substring.
Example 3:

Input: String="bcdxabcdy", Pattern="bcdyabcdx"
Output: true
Explanation: Both the string and the pattern are a permutation of each other.
Example 4:

Input: String="aaacb", Pattern="abc"
Output: true
Explanation: The string contains "acb" which is a permutation of the given pattern.
 */

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class StringPermutation {
    public static boolean findPermutation(String str, String pattern) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: pattern.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch, 0)+1);
        }
        int windowStart =0, matches =0;
        for(int windowEnd=0;windowEnd<str.length();windowEnd++){
            char rightChar = str.charAt(windowEnd);
            if(freqMap.containsKey(rightChar)){
                freqMap.put(rightChar, freqMap.get(rightChar)-1);
                if(freqMap.get(rightChar)==0)matches++;
            }
            if(matches==freqMap.size())return true;
            if(windowEnd>=pattern.length()-1){
                char leftChar = str.charAt(windowStart);
                if (freqMap.containsKey(leftChar)){
                    if(freqMap.get(leftChar)==0){
                        matches--;
                    }
                    freqMap.put(leftChar,freqMap.get(leftChar)+1);
                }
                windowStart++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("oidbcaf", "abc"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("odicf", "dc"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: " + StringPermutation.findPermutation("aaacb", "abc"));
    }
}
