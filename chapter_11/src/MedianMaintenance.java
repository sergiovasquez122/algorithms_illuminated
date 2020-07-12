import edu.princeton.cs.algs4.AVLTreeST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;

public class MedianMaintenance {
    public static void main(String[] args) {
        In in = new In(args[0]);
        Queue<Integer> result = new Queue<>();
        MinPQ<Integer> minpq = new MinPQ<>(10);
        MaxPQ<Integer> maxpq = new MaxPQ<>(10);
        while(!in.isEmpty()){
            minpq.insert(in.readInt());
            maxpq.insert(minpq.delMax());
            if(maxpq.size() > minpq.size()){
                minpq.insert(maxpq.delMax());
            }

            int value = (maxpq.size() == minpq.size()) ? maxpq.peek() : minpq.peek();
            result.enqueue(value);
        }

        int running_sum = 0;
        for(int e : result){
            running_sum += e;
        }
        System.out.println(running_sum);

        in = new In(args[0]);

        AVLTreeST<Integer, Integer> avl = new AVLTreeST<>();
        Queue<Integer> values = new Queue<>();
        while(!in.isEmpty()){
            avl.put(in.readInt(), 1);
            int k = avl.size() - 1;
            if(k % 2 == 0){
                int select = (k + 1) / 2;
                values.enqueue(avl.select(select));
            } else {
                int select = k / 2;
                values.enqueue(avl.select(select));
            }
        }

        int running_sum2 = 0;
        for(int e : values){
            running_sum2 += e;
        }
        System.out.println(running_sum2);
    }
}

