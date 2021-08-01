/*
Given a string with lowercase letters only, if you are allowed to replace no more than ‘k’ letters with any letter,
find the length of the longest substring having the same letters after replacement.

Example 1:

Input: String="aabccbb", k=2
Output: 5
Explanation: Replace the two 'c' with 'b' to have a longest repeating substring "bbbbb".
Example 2:

Input: String="abbcb", k=1
Output: 4
Explanation: Replace the 'c' with 'b' to have a longest repeating substring "bbbb".
Example 3:

Input: String="abccde", k=1
Output: 3
Explanation: Replace the 'b' or 'd' with 'c' to have the longest repeating substring "ccc".
 */

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CharacterReplacement {
    public static int findLength(String str, int k) {
        int windowStart =0, maxRepeatingLength=0, maxLength =0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int windowEnd =0;windowEnd<str.length();windowEnd++){
            char rightChar = str.charAt(windowEnd);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar,0)+1);
            maxRepeatingLength = Math.max(maxRepeatingLength, freqMap.get(rightChar));
            if(windowEnd-windowStart+1-maxRepeatingLength>k){
                char leftChar = str.charAt(windowStart);
                freqMap.put(leftChar, freqMap.get(leftChar)-1);
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd-windowStart+1);
        }
        return maxLength;
    }
    public static void main(String[] args) {
        System.out.println(CharacterReplacement.findLength("aabccbb", 2));
        System.out.println(CharacterReplacement.findLength("abbcb", 1));
        System.out.println(CharacterReplacement.findLength("abccde", 1));
    }
}
