package kwaymerge;
/*
Given an N * NN∗N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the matrix.

Example 1:

Input: Matrix=[
    [2, 6, 8],
    [3, 7, 10],
    [5, 8, 11]
  ],
  K=5
Output: 7
Explanation: The 5th smallest number in the matrix is 7.
 */
import java.util.PriorityQueue;

class RowColNode{
    int row,col;
    public RowColNode(int row, int col){
        this.row = row;
        this.col = col;
    }
}

public class KthSmallestInSortedMatrix {
    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<RowColNode> minHeap = new PriorityQueue<>((p1,p2)->matrix[p1.row][p1.col]-matrix[p2.row][p2.col]);
        for(int i=0;i<matrix.length;i++){
            minHeap.add(new RowColNode(i, 0));
        }
        int resultCount=0, result=-1;
        while(!minHeap.isEmpty()){
            RowColNode node = minHeap.poll();
            if(++resultCount==k){
                result = matrix[node.row][node.col];
                break;
            }
            node.col++;
            if(matrix[node.row].length>node.col){
                minHeap.add(new RowColNode(node.row, node.col));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}
