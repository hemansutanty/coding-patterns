package topologicalsort;
/*
Given a sequence originalSeq and an array of sequences, write a method to find if originalSeq can be uniquely reconstructed from the array of sequences.

Unique reconstruction means that we need to find if originalSeq is the only sequence such that all sequences in the array are subsequences of it.

Example 1:

Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [3, 4]]
Output: true
Explanation: The sequences [1, 2], [2, 3], and [3, 4] can uniquely reconstruct
[1, 2, 3, 4], in other words, all the given sequences uniquely define the order of numbers
in the 'originalSeq'.
Example 2:

Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [2, 4]]
Output: false
Explanation: The sequences [1, 2], [2, 3], and [2, 4] cannot uniquely reconstruct
[1, 2, 3, 4]. There are two possible sequences we can construct from the given sequences:
1) [1, 2, 3, 4]
2) [1, 2, 4, 3]
Example 3:

Input: originalSeq: [3, 1, 4, 2, 5], seqs: [[3, 1, 5], [1, 4, 2, 5]]
Output: true
Explanation: The sequences [3, 1, 5] and [1, 4, 2, 5] can uniquely reconstruct
[3, 1, 4, 2, 5].


Time Complexity : O(V+N)
 */

import java.util.*;

public class SequenceReconstruction {
    public static boolean canConstruct(int[] originalSeq, int[][] sequences) {
        if(originalSeq.length<=0) return false;

        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for(int[] seq: sequences){
            for(int i=0;i<seq.length;i++){
                inDegree.putIfAbsent(seq[i], 0);
                graph.putIfAbsent(seq[i], new ArrayList<>());
            }
        }

        for(int[] seq: sequences){
            for(int i=0;i<seq.length-1;i++){
                int parent = seq[i], child = seq[i+1];
                inDegree.put(child, inDegree.get(child)+1);
                graph.get(parent).add(child);
            }
        }

        if(inDegree.size()!=originalSeq.length)return false;// Imp condition
        Queue<Integer> sources = new LinkedList<>();
        for(Map.Entry<Integer, Integer> entry: inDegree.entrySet()){
            if(entry.getValue()==0)sources.add(entry.getKey());
        }
        List<Integer> sortedOrder = new ArrayList<>();
        while(!sources.isEmpty()){
            if(sources.size()>1) return false; // Imp condition
            if(sources.peek()!=originalSeq[sortedOrder.size()])return false; // Imp condition
            int number = sources.poll();
            sortedOrder.add(number);
            List<Integer> children = graph.get(number);
            for(int child: children){
                inDegree.put(child, inDegree.get(child)-1);
                if(inDegree.get(child)==0)sources.add(child);
            }
        }
        return sortedOrder.size()==originalSeq.length;
    }

    public static void main(String[] args) {
        boolean result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
                new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 3, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 1, 2, 3, 4 },
                new int[][] { new int[] { 1, 2 }, new int[] { 2, 3 }, new int[] { 2, 4 } });
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[] { 3, 1, 4, 2, 5 },
                new int[][] { new int[] { 3, 1, 5 }, new int[] { 1, 4, 2, 5 } });
        System.out.println("Can we uniquely construct the sequence: " + result);
    }

}
