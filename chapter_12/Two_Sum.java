import edu.princeton.cs.algs4.SeparateChainingHashST;

public class Two_Sum {
    public static boolean has_solution(int arr[], int t){
        SeparateChainingHashST<Integer, Integer> hash_table = new SeparateChainingHashST<>();
        for(int e : arr) hash_table.put(e, e);
        for(int e : arr) if(hash_table.contains(t - e)) return true;
        return false;
    }
}
