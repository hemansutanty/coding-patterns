package kwaymerge;
/*
Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.

Example 1:

Input: L1=[1, 5, 8], L2=[4, 12], L3=[7, 8, 10]
Output: [4, 7]
Explanation: The range [4, 7] includes 5 from L1, 4 from L2 and 7 from L3.
Example 2:

Input: L1=[1, 9], L2=[4, 12], L3=[7, 10, 16]
Output: [9, 12]
Explanation: The range [9, 12] includes 9 from L1, 12 from L2 and 10 from L3.

Time : O(NlogM)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class RangeNode{
    int arrayIndex,elementIndex;
    public RangeNode(int arrayIndex, int elementIndex){
        this.arrayIndex=arrayIndex;
        this.elementIndex=elementIndex;
    }
}
public class SmallestRange {
    public static int[] findSmallestRange(List<Integer[]> lists) {
        PriorityQueue<RangeNode> minHeap = new PriorityQueue<>((p1,p2)->lists.get(p1.arrayIndex)[p1.elementIndex]-lists.get(p2.arrayIndex)[p2.elementIndex]);
        int currentMax = Integer.MIN_VALUE,rangeStart=0,rangeEnd=Integer.MAX_VALUE;
        for(int i=0;i<lists.size();i++){
            minHeap.add(new RangeNode(i,0));
            currentMax=Math.max(currentMax, lists.get(i)[0]);
        }
        while(minHeap.size()== lists.size()){
            RangeNode node= minHeap.poll();
            if(currentMax-lists.get(node.arrayIndex)[node.elementIndex]<rangeEnd-rangeStart){
                rangeStart=lists.get(node.arrayIndex)[node.elementIndex];
                rangeEnd=currentMax;
            }
            node.elementIndex++;
            if(lists.get(node.arrayIndex).length>node.elementIndex){
                minHeap.add(new RangeNode(node.arrayIndex, node.elementIndex));
                currentMax=Math.max(currentMax, lists.get(node.arrayIndex)[node.elementIndex]);
            }
        }
        return new int[] { rangeStart, rangeEnd };
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 1, 5, 8 };
        Integer[] l2 = new Integer[] { 4, 12 };
        Integer[] l3 = new Integer[] { 7, 8, 10 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int[] result = SmallestRange.findSmallestRange(lists);
        System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
    }
}
