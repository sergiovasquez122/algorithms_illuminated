import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class QuickSort4 {
    private static void swap(int A[], int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    private static int sort(int A[]){
        return QuickSort(A, 0, A.length - 1);
    }

    private static int QuickSort(int A[], int l, int r){
        if(l >= r) return 0;
        int i = medianOfThree(A, l, r);
        swap(A, l, i);
        int j = partition(A, l, r);
        int left_comparisons = QuickSort(A, l, j - 1);
        int right_comparison = QuickSort(A, j + 1, r);
        return (r - l) + left_comparisons + right_comparison;
    }

    private static int medianOfThree(int A[], int l, int r){
        int mid = (l + r) / 2;
        int middle  = A[mid];
        if(A[l] < middle && middle < A[r] || A[r] < middle && middle < A[l]){
            return mid;
        } else if(middle < A[l] && A[l] < A[r] || A[r] < A[l] &&  A[l] < middle){
            return l;
        } else {
            return r;
        }
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
        StdOut.println(sort(A));
    }
}
