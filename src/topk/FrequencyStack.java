package topk;

/*
Design a class that simulates a Stack data structure, implementing the following two operations:

push(int num): Pushes the number ‘num’ on the stack.
pop(): Returns the most frequent number in the stack. If there is a tie, return the number which was pushed later.
Example:

After following push operations: push(1), push(2), push(3), push(2), push(1), push(2), push(5)

1. pop() should return 2, as it is the most frequent number
2. Next pop() should return 1
3. Next pop() should return 2

Push: O(logn)
Pop: O(logn)
 */

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Element{
    int number;
    int count;
    int sequenceNumber;
    public Element(int number, int count, int sequenceNumber){
    }
}

class ElementComparator implements Comparator<Element>{
    @Override
    public int compare(Element o1, Element o2) {
        if(o1.count != o2.count)return o2.count-o1.count;
        return o2.sequenceNumber-o1.sequenceNumber;
    }
}

public class FrequencyStack {
    int seqNumber = 0 ;
    Map<Integer, Integer> freqMap = new HashMap<>();
    PriorityQueue<Element> maxHeap = new PriorityQueue<>(new ElementComparator());
    public void push(int num) {
        freqMap.put(num, freqMap.getOrDefault(num,0)+1);
        maxHeap.offer(new Element(num, freqMap.get(num)+1, seqNumber++));
    }
    public int pop() {
        Element top = maxHeap.poll();
        if(top!=null){
            freqMap.put(top.number, freqMap.get(top.number)-1);

        }
        return top==null?-1:top.number;
    }
    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}
