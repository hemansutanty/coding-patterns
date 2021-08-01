/*
Given a string and a pattern, find the smallest substring in the given string which has all the characters of the given pattern.

Example 1:

Input: String="aabdec", Pattern="abc"
Output: "abdec"
Explanation: The smallest substring having all characters of the pattern is "abdec"
Example 2:

Input: String="abdbca", Pattern="abc"
Output: "bca"
Explanation: The smallest substring having all characters of the pattern is "bca".
Example 3:

Input: String="adcad", Pattern="abc"
Output: ""
Explanation: No substring in the given string has all characters of the pattern.
 */

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static String findSubstring(String str, String pattern) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char ch: pattern.toCharArray()){
            freqMap.put(ch, freqMap.getOrDefault(ch,0)+1);
        }
        int matches=0, minLength=str.length()+1,windowStart=0, subStrStart=0;
        for(int windowEnd =0; windowEnd<str.length();windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (freqMap.containsKey(rightChar)) {
                freqMap.put(rightChar, freqMap.get(rightChar) - 1);
                if (freqMap.get(rightChar) >= 0) matches++;
            }
            // shrink the window if we can, finish as soon as we remove a matched character
            while (matches == pattern.length()) {
                if (minLength > windowEnd - windowStart + 1) {
                    minLength = windowEnd - windowStart + 1;
                    subStrStart = windowStart;
                }

                char leftChar = str.charAt(windowStart++);
                if (freqMap.containsKey(leftChar)) {
                    // note that we could have redundant matching characters, therefore we'll decrement the
                    // matched count only when a useful occurrence of a matched character is going out of the window
                    if (freqMap.get(leftChar) == 0)
                        matches--;
                    freqMap.put(leftChar, freqMap.get(leftChar) + 1);
                }
            }
        }
        return minLength > str.length() ? "" : str.substring(subStrStart, subStrStart + minLength);
    }
    public static void main(String[] args) {
        System.out.println(MinimumWindowSubstring.findSubstring("aabdec", "abc"));
        System.out.println(MinimumWindowSubstring.findSubstring("abdbca", "abc"));
        System.out.println(MinimumWindowSubstring.findSubstring("adcad", "abc"));
    }
}
