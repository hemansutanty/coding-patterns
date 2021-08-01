/*
Given a string, find the length of the longest substring, which has no repeating characters.

Example 1:

Input: String="aabccbb"
Output: 3
Explanation: The longest substring without any repeating characters is "abc".
Example 2:

Input: String="abbbb"
Output: 2
Explanation: The longest substring without any repeating characters is "ab".
Example 3:

Input: String="abccde"
Output: 3
Explanation: Longest substrings without any repeating characters are "abc" & "cde".
 */

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubstring {
    public static int findLength(String str) {
        int windowStart =0, maxLength =0;
        Map<Character, Integer> charIndex = new HashMap<>();
        for(int windowEnd =0;windowEnd<str.length();windowEnd++){
            char rightChar = str.charAt(windowEnd);
            if(charIndex.containsKey(rightChar)){
                windowStart = Math.max(windowStart, charIndex.get(rightChar)+1);
            }
            charIndex.put(rightChar, windowEnd);
            maxLength = Math.max(maxLength, windowEnd-windowStart+1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("aabccbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abbbb"));
        System.out.println("Length of the longest substring: " + NoRepeatSubstring.findLength("abccde"));
    }
}
