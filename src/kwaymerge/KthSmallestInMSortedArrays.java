package kwaymerge;

/*
iven ‘M’ sorted arrays, find the K’th smallest number among all the arrays.

Example 1:

Input: L1=[2, 6, 8], L2=[3, 6, 7], L3=[1, 3, 4], K=5
Output: 4
Explanation: The 5th smallest number among all the arrays is 4, this can be verified from
the merged list of all the arrays: [1, 2, 3, 3, 4, 6, 6, 7, 8]
Example 2:

Input: L1=[5, 8, 9], L2=[1, 7], K=3
Output: 7
Explanation: The 3rd smallest number among all the arrays is 7.

Similar Problems :


Problem 1: Given ‘M’ sorted arrays, find the median number among all arrays.

Solution: This problem is similar to our parent problem with K=Median.
So if there are ‘N’ total numbers in all the arrays we need to find the K’th minimum number where K=N/2K=N/2.

Problem 2: Given a list of ‘K’ sorted arrays, merge them into one sorted list.

Solution: This problem is similar to Merge K Sorted Lists except that the input is a list of arrays compared to LinkedLists.
To handle this, we can use a similar approach as discussed in our parent problem by keeping a track of the array and the element indices.


Time: O(KlogM)
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
class Node{
    int arrayIndex;
    int elementIndex;
    public Node(int arrayIndex, int elementIndex){
        this .arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}
public class KthSmallestInMSortedArrays {
    public static int findKthSmallest(List<Integer[]> lists, int k) {
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((p1,p2)->lists.get(p1.arrayIndex)[p1.elementIndex]-lists.get(p2.arrayIndex)[p2.elementIndex]);
        for(int i=0;i<lists.size();i++){
            maxHeap.add(new Node(i,0));
        }
        int resultCount = 0; int result=-1;
        while(!maxHeap.isEmpty()){
            Node node = maxHeap.poll();
            result=lists.get(node.arrayIndex)[node.elementIndex];
            if(++resultCount==k)break;
            node.elementIndex++;
            if(lists.get(node.arrayIndex).length>node.elementIndex){
                maxHeap.add(new Node(node.arrayIndex,node.elementIndex));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = KthSmallestInMSortedArrays.findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}
