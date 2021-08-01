/*
Given a string, find the length of the longest substring in it with no more than K distinct characters.

You can assume that K is less than or equal to the length of the given string.

Example 1:

Input: String="araaci", K=2
Output: 4
Explanation: The longest substring with no more than '2' distinct characters is "araa".
Example 2:

Input: String="araaci", K=1
Output: 2
Explanation: The longest substring with no more than '1' distinct characters is "aa".
Example 3:

Input: String="cbbebi", K=3
Output: 5
Explanation: The longest substrings with no more than '3' distinct characters are "cbbeb" & "bbebi".
 */
package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistinct {
    public static int findLength(String str, int k) {
        if(str.length()<k)return -1;
        int maxLength = 0,windowStart=0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int windowEnd =0;windowEnd<str.length();windowEnd++){
            char rightChar = str.charAt(windowEnd);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar,0)+1);
            while(freqMap.size()>k){
                char leftChar = str.charAt(windowStart);
                freqMap.put(leftChar,freqMap.get(str.charAt(windowStart))-1);
                if(freqMap.get(str.charAt(windowStart))==0) freqMap.remove(leftChar);
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd-windowStart+1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 2));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("araaci", 1));
        System.out.println("Length of the longest substring: " + LongestSubstringKDistinct.findLength("cbbebi", 3));
    }
}
