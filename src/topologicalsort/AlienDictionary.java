package topologicalsort;
/*
There is a dictionary containing words from an alien language for which we don’t know the ordering of the alphabets.
Write a method to find the correct order of the alphabets in the alien language.
It is given that the input is a valid dictionary and there exists an ordering among its alphabets.

Example 1:

Input: Words: ["ba", "bc", "ac", "cab"]
Output: bac
Explanation: Given that the words are sorted lexicographically by the rules of the alien language, so
from the given words we can conclude the following ordering among its characters:

1. From "ba" and "bc", we can conclude that 'a' comes before 'c'.
2. From "bc" and "ac", we can conclude that 'b' comes before 'a'

From the above two points, we can conclude that the correct character order is: "bac"
Example 2:

Input: Words: ["cab", "aaa", "aab"]
Output: cab
Explanation: From the given words we can conclude the following ordering among its characters:

1. From "cab" and "aaa", we can conclude that 'c' comes before 'a'.
2. From "aaa" and "aab", we can conclude that 'a' comes before 'b'

From the above two points, we can conclude that the correct character order is: "cab"
Example 3:

Input: Words: ["ywx", "wz", "xww", "xz", "zyy", "zwz"]
Output: ywxz
Explanation: From the given words we can conclude the following ordering among its characters:

1. From "ywx" and "wz", we can conclude that 'y' comes before 'w'.
2. From "wz" and "xww", we can conclude that 'w' comes before 'x'.
3. From "xww" and "xz", we can conclude that 'w' comes before 'z'
4. From "xz" and "zyy", we can conclude that 'x' comes before 'z'
5. From "zyy" and "zwz", we can conclude that 'y' comes before 'w'

From the above five points, we can conclude that the correct character order is: "ywxz"


Time complexity : O(V+N), N : no of words
 */

import java.util.*;

public class AlienDictionary {
    public static String findOrder(String[] words) {
        if(words == null || words.length==0) return "";

        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, List<Character>> graph = new HashMap<>();
        for(String word: words){
            for(char ch: word.toCharArray()){
                inDegree.put(ch, 0);
                graph.put(ch, new ArrayList<>());
            }
        }
        for(int i=0;i<words.length-1;i++){
            String word1 = words[i], word2= words[i+1];
            for(int j=0;j<Math.min(word1.length(), word2.length());j++){
                char parent = word1.charAt(j), child = word2.charAt(j);
                if(parent!=child){
                    inDegree.put(child, inDegree.get(child)+1);
                    graph.get(parent).add(child);
                    // only the first different character between the two words will help us find the order
                    break;
                }

            }
        }

        Queue<Character> sources = new LinkedList<>();
        for(Map.Entry<Character, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0) sources.add(entry.getKey());
        }
        StringBuilder order = new StringBuilder();
        while(!sources.isEmpty()){
            char ch = sources.poll();
            order.append(ch);
            List<Character> children = graph.get(ch);
            for(char child: children){
                inDegree.put(child,inDegree.get(child)-1);
                if(inDegree.get(child)==0)sources.add(child);
            }
        }
        if(order.length()!=inDegree.size())return "";
        return order.toString();
    }

    public static void main(String[] args) {
        String result = AlienDictionary.findOrder(new String[] { "ba", "bc", "ac", "cab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "cab", "aaa", "aab" });
        System.out.println("Character order: " + result);

        result = AlienDictionary.findOrder(new String[] { "ywx", "wz", "xww", "xz", "zyy", "zwz" });
        System.out.println("Character order: " + result);
    }
}
