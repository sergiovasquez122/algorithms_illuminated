import edu.princeton.cs.algs4.StdOut;

public class WIS {
    public static int compute(int pathGraph[]){
        int[] A = new int[pathGraph.length + 1];
        A[0] = 0;
        A[1] = pathGraph[0];
        for(int i = 2;i <= pathGraph.length;i++){
            A[i] = Math.max(A[i - 1], A[i - 2] + pathGraph[i - 1]);
        }
        return A[pathGraph.length];
    }

    public static void main(String[] args) {
        int[] A = {1, 4, 5, 4};
        StdOut.println(compute(A));
        int[] B = {3, 2, 1, 6, 4, 5};
        StdOut.println(compute(B));
    }
}
