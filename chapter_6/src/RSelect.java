import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RSelect {

    private static int kth_smallest(int[] A, int k){
        int left = 0, right = A.length - 1;
        while(left <= right){
            int pivot = StdRandom.uniform(left, right + 1);
            swap(A, left, pivot);
            int j = partition(A, left, right);
            if(j == k - 1){
                return A[pivot];
            } else if(j < k - 1) {
                left = j + 1;
            } else{
                right = j - 1;
            }
        }
        return -1;
    }

    private static int partition(int A[], int l, int r){
        int p = A[l];
        int i = l + 1;
        for(int j = l + 1;j <= r; ++j){
            if(A[j] < p){
                swap(A, i, j);
                i++;
            }
        }
        swap(A, l, i - 1);
        return i - 1;
    }

    private static void swap(int A[], int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Queue<Integer> array = new Queue<>();
        while(!in.isEmpty()){
            array.enqueue(in.readInt());
        }
        int[] A = new int[array.size()];
        int counter = 0;
        for(int i : array){
            A[counter++] = i;
        }
        StdOut.println(kth_smallest(A, 50));
    }
}
