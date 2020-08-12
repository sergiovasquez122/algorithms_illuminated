import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdOut;

public class GreedyDiff {

    private static class Node implements Comparable<Node> {
        private int weight;
        private int length;
        public Node(int weight, int length){
            this.weight = weight;
            this.length = length;
        }

        @Override
        public int compareTo(Node o) {
            int thisCost = weight - length;
            int theirCost = o.weight - o.length;
            if(thisCost < theirCost) return -1;
            else if(thisCost > theirCost) return 1;
            else {
                return Integer.compare(weight, o.weight);
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MaxPQ<Node> maxPQ = new MaxPQ<>();
        in.readLine();
        while(in.hasNextLine()){
            int weight = in.readInt();
            int height = in.readInt();
            maxPQ.insert(new Node(weight, height));
        }
        int completion_time = 0;
        int running_sum = 0;
        while(!maxPQ.isEmpty()){
            Node x = maxPQ.delMax();
            running_sum += x.length;
            completion_time += running_sum * x.weight;
        }
        StdOut.println(completion_time);
    }
}
