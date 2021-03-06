import edu.princeton.cs.algs4.StdOut;

public class Inversions {
    private static Comparable[] aux;

    public static int inversion(Comparable[] a){
        aux = new Comparable[a.length];
        return sort(a, 0, a.length - 1);
    }

    public static int sort(Comparable[] a, int lo,int hi){
        if(hi <= lo) return 0;
        int mid = lo + (hi - lo) / 2;
        int left_inversions = sort(a, lo, mid);
        int right_inversions = sort(a, mid + 1, hi);
        return merge(a, lo, mid, hi) + left_inversions + right_inversions;
    }

    public static int merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo, j = mid + 1;
        for(int k = lo;k <= hi; k++)
            aux[k] = a[k];

        int inversions = 0;
        for(int k = lo;k <= hi;k++){
            if(i > mid) a[k] = aux[j++];
            else if(j > hi) a[k] = aux[i++];
            else if(aux[i].compareTo(aux[j]) < 0) a[k] = aux[i++];
            else{
                inversions += mid - i + 1;
                a[k] = aux[j++];
            }

        }
        return inversions;
    }

    public static boolean less(Comparable u, Comparable w){
        return u.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Integer arr[] = {7, 6, 5, 4, 3, 2, 1};
        StdOut.println(inversion(arr));
    }
}
