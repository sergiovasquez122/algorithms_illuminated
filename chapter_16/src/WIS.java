import edu.princeton.cs.algs4.Queue;
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

    public static int[] WISconstruction(int pathGraph[]){
        int[] A = new int[pathGraph.length + 1];
        A[0] = 0;
        A[1] = pathGraph[0];
        for(int i = 2;i <= pathGraph.length;i++){
            A[i] = Math.max(A[i - 1], A[i - 2] + pathGraph[i - 1]);
        }
        return A;
    }

    public static Iterable<Integer> WISReconstruction(int pathGraph[]){
        int[] A = WISconstruction(pathGraph);
        Queue<Integer> vertices = new Queue<>();
        int idx = pathGraph.length;
        while(idx >= 2){
            if(A[idx - 1] >= A[idx - 2] + pathGraph[idx - 1]){
               idx--;
            } else {
                vertices.enqueue(idx - 1);
                idx-=2;
            }
        }
        if(idx == 1) vertices.enqueue(0);
        return vertices;
    }

    public static void main(String[] args) {
        int[] A = {1, 4, 5, 4};
        StdOut.println(compute(A));
        int[] B = {3, 2, 1, 6, 4, 5};
        StdOut.println(compute(B));
        for(int v : WISReconstruction(B)){
            StdOut.print(" " + v);
        }
        StdOut.println();
    }
}
