public class QuickSort {

    private static void swap(int A[], int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    private static void sort(int A[]){

    }

    private static int alwaysFirst(int A[], int l, int r){
        return l;
    }

    private static int alwaysLast(int A[], int l, int r){
        return r;
    }

    private static void quickSort1(int A[], int l, int r){
        if(l >= r) return;
        int i = alwaysFirst(A, l, r);
        swap(A, l, i);
        int j = partition(A, l, r);
        quickSort1(A, l, j - 1);
        quickSort1(A, j + 1, r);
    }

    private static void quickSort2(int A[], int l, int r){
        if(l >= r) return;
        int i = alwaysLast(A, l, r);
        swap(A, l, i);
        int j = partition(A, l, r);
        quickSort2(A, l, r);
        quickSort2(A, j + 1, r);
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
