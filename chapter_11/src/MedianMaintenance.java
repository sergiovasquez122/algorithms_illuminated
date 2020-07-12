import edu.princeton.cs.algs4.AVLTreeST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MedianMaintenance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        Queue<Double> result = new Queue<>();
        MinPQ<Integer> minpq = new MinPQ<>(10);
        MaxPQ<Integer> maxpq = new MaxPQ<>(10);
        while(!in.isEmpty()){
            minpq.insert(in.readInt());
            maxpq.insert(minpq.delMax());
            if(maxpq.size() > minpq.size()){
                minpq.insert(maxpq.delMax());
            }

            double value = (maxpq.size() == minpq.size()) ? maxpq.peek() : minpq.peek();
            result.enqueue(value);
        }

        double running_sum = 0;
        for(double e : result){
            running_sum += e;
        }
        System.out.println(running_sum);

    }
}
