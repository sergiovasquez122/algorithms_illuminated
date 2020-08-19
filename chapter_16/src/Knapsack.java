import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Knapsack {

    public static int knapsack(int [] values, int weights[], int C){
        int[][] A = new int[values.length + 1][C + 1];
        for(int i = 1;i <= values.length;i++){
            for(int c = 0;c <= C;c++){
                if(weights[i - 1] > c){
                    A[i][c] = A[i - 1][c];
                } else{
                    A[i][c] = Math.max(A[i - 1][c], A[i - 1][c - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return A[values.length][C];
    }

    public static Iterable<Integer> knapsackReconstruction(int[] values, int[] weights, int C){
        int[][] A = new int[values.length + 1][C + 1];
        for(int i = 1;i <= values.length;i++){
            for(int c = 0;c <= C;c++){
                if(weights[i - 1] > c){
                    A[i][c] = A[i - 1][c];
                } else{
                    A[i][c] = Math.max(A[i - 1][c], A[i - 1][c - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        Stack<Integer> onStack = new Stack<>();
        int c = C;
        for(int i = values.length;i >= 1;i--){
            if(weights[i - 1] <= c && A[i - 1][c - weights[i - 1]] + values[i - 1] >= A[i - 1][c]){
                onStack.push(i - 1);
                c = c - weights[i - 1];
            }
        }
        return onStack;
    }

    public static void main(String[] args) {
        int[] values1 = {3, 2, 4, 4};
        int[] size1 = {4, 3, 2, 3};
        StdOut.println(knapsack(values1, size1, 6));
        for(int w : knapsackReconstruction(values1, size1, 6)){
            StdOut.print(" " + w);
        }
        StdOut.println();

        In in = new In(args[0]);
        int knapsackSize = in.readInt();
        int numberOfItems = in.readInt();
        int[] values = new int[numberOfItems];
        int[] weights = new int[numberOfItems];
        for(int i = 0;i < numberOfItems;i++){
            values[i] = in.readInt();
            weights[i] = in.readInt();
        }
        StdOut.println(knapsack(values, weights, knapsackSize));
    }
}
