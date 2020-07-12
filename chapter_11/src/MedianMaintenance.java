import edu.princeton.cs.algs4.AVLTreeST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MedianMaintenance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        AVLTreeST<Integer, Integer> avltree = new AVLTreeST<>();
        Queue<Double> result = new Queue<>();
        int k = 0;
        while(!in.isEmpty()){
            avltree.put(in.readInt(), k);
            if(avltree.size() == 1){
                result.enqueue((double)avltree.min());
            }
            else if(avltree.size() % 2 == 1){
                result.enqueue((double)avltree.select(k));
            } else {
                int kth = avltree.select(k);
                int kth_plus_one = avltree.select(k - 1);
                result.enqueue((kth + kth_plus_one) / 2.0);
            }
            k++;
        }
        System.out.println(result);
    }
}
