public class QuickSort {

    private static void swap(int A[], int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void sort(int A[]){

    }

    private static int naiveChoosePivot(int A[], int l, int r){
        return l;
    }

    private static void quickSort(int A[], int l, int r){
        if(l >= r) return;
        int i = naiveChoosePivot(A, l, r);
        swap(A, l, i);
        int j = partition(A, l, r);
        quickSort(A, l, j - 1);
        quickSort(A, j + 1, r);
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
}
