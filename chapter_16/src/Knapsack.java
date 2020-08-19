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

    public static void main(String[] args) {
        int[] values1 = {3, 2, 4, 4};
        int[] size1 = {4, 3, 2, 3};
        StdOut.println(knapsack(values1, size1, 6));
    }
}
