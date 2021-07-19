package twoheaps;
/*
Given an array of numbers and a number ‘k’, find the median of all the ‘k’ sized sub-arrays (or windows) of the array.

Example 1:

Input: nums=[1, 2, -1, 3, 5], k = 2
Output: [1.5, 0.5, 1.0, 4.0]
Explanation: Lets consider all windows of size ‘2’:

[1, 2, -1, 3, 5] -> median is 1.5
[1, 2, -1, 3, 5] -> median is 0.5
[1, 2, -1, 3, 5] -> median is 1.0
[1, 2, -1, 3, 5] -> median is 4.0
Example 2:

Input: nums=[1, 2, -1, 3, 5], k = 3
Output: [1.0, 2.0, 3.0]
Explanation: Lets consider all windows of size ‘3’:

[1, 2, -1, 3, 5] -> median is 1.0
[1, 2, -1, 3, 5] -> median is 2.0
[1, 2, -1, 3, 5] -> median is 3.0

Time : O(N*K)
 */

import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public double[] findSlidingWindowMedian(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];

        for(int i=0;i<nums.length;i++){
            if(maxHeap.size()==0 || maxHeap.peek()>=nums[i]){
                maxHeap.add(nums[i]);
            }else{
                minHeap.add(nums[i]);
            }
            rebalanceHeaps();
            if(i-k+1>=0){
                if(maxHeap.size()==minHeap.size()){
                    result[i-k+1]=(maxHeap.peek()+minHeap.peek())/2.0;
                }else{
                    result[i-k+1]=maxHeap.peek();
                }
                int elementToBeRemoved = nums[i-k+1];
                if(elementToBeRemoved<=maxHeap.peek()){
                    maxHeap.remove(elementToBeRemoved);
                }else{
                    minHeap.remove(elementToBeRemoved);
                }
                rebalanceHeaps();
            }
        }
        return result;
    }
    public void rebalanceHeaps(){
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }
    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        double[] result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 2);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
        System.out.println();

        slidingWindowMedian = new SlidingWindowMedian();
        result = slidingWindowMedian.findSlidingWindowMedian(new int[] { 1, 2, -1, 3, 5 }, 3);
        System.out.print("Sliding window medians are: ");
        for (double num : result)
            System.out.print(num + " ");
    }
}
