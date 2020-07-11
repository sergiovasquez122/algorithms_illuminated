import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MedianMaintenance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        MaxPQ<Integer> maxpq = new MaxPQ<>(10);
        MinPQ<Integer> minpq = new MinPQ<>(10);
        Queue<Double> result = new Queue<>();

        while(!in.isEmpty()){

        }

        System.out.println(result);
    }
}
