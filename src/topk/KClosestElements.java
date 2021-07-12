package topk;

/*
Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array. Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.

Example 1:

Input: [5, 6, 7, 8, 9], K = 3, X = 7
Output: [6, 7, 8]
Example 2:

Input: [2, 4, 5, 6, 9], K = 3, X = 6
Output: [4, 5, 6]
Example 3:

Input: [2, 4, 5, 6, 9], K = 3, X = 10
Output: [5, 6, 9]

time: O(k*logK)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
class Entry {
    int key;
    int value;

    public Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class KClosestElements {

    public static List<Integer> findClosestElements(int[] arr, int K, Integer X) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Entry> maxHeap = new PriorityQueue<>((p1,p2)->p2.value-p1.value);
        for(int ar: arr){
            Entry entry = new Entry(ar, Math.abs(X-ar));
            maxHeap.add(entry);
            if(maxHeap.size()>K)maxHeap.poll();
        }
        while(maxHeap.size()>0){
            result.add(maxHeap.poll().key);
        }
        Collections.sort(result);
        return result;
    }

    public static void main(String[] args) {
        List<Integer> result = KClosestElements.findClosestElements(new int[] { 5, 6, 7, 8, 9 }, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);

        result = KClosestElements.findClosestElements(new int[] { 2, 4, 5, 6, 9 }, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
    }
}
