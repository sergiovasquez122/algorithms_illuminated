import edu.princeton.cs.algs4.StdOut;

public class QuickSort1 {

    private static int alwaysFirst(int A[], int l, int r){
        return l;
    }

    private static int sort(int A[]){
        return QuickSort(A, 0, A.length - 1);
    }

    private static int QuickSort(int A[], int l, int r){
        if(l >= r) return 0;
        int i = alwaysFirst(A, l, r);
        swap(A, l, i);
        int j = partition(A, l, r);
        int left_comparisons = QuickSort(A, l, j - 1);
        int right_comparison = QuickSort(A, j + 1, r);
        return (r - l) + left_comparisons + right_comparison;
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
        int[] A = {2148, 9058, 7742, 3153, 6324, 609, 7628, 5469, 7017, 504};
        StdOut.println(sort(A));
    }
}
