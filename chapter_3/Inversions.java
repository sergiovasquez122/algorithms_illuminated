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
        return 0;
    }
}
