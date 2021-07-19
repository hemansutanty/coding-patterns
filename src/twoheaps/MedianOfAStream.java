package twoheaps;
/*
Design a class to calculate the median of a number stream. The class should have the following two methods:

insertNum(int num): stores the number in the class
findMedian(): returns the median of all numbers inserted in the class
If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.

Example 1:

1. insertNum(3)
2. insertNum(1)
3. findMedian() -> output: 2
4. insertNum(5)
5. findMedian() -> output: 3
6. insertNum(4)
7. findMedian() -> output: 3.5


Time: Insert : O(logN)
Find Median: O(1)
 */

import java.util.PriorityQueue;

public class MedianOfAStream {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianOfAStream(){
        maxHeap = new PriorityQueue<>((a,b)->b-a);
        minHeap = new PriorityQueue<>();
    }

    public void insertNum(int num) {
        if(maxHeap.isEmpty()||maxHeap.peek()>=num)maxHeap.add(num);
        else minHeap.add(num);

        if(maxHeap.size()>minHeap.size()+1){
            minHeap.add(maxHeap.poll());
        }else if(maxHeap.size()<minHeap.size()){
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if(maxHeap.size()==minHeap.size()){
            return (maxHeap.peek()+minHeap.peek())/2.0;
        }else{
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianOfAStream medianOfAStream = new MedianOfAStream();
        medianOfAStream.insertNum(3);
        medianOfAStream.insertNum(1);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(5);
        System.out.println("The median is: " + medianOfAStream.findMedian());
        medianOfAStream.insertNum(4);
        System.out.println("The median is: " + medianOfAStream.findMedian());
    }
}
