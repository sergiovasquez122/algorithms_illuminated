import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class timing_test {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int V = in.readInt(), E = in.readInt();
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for(int i = 0;i < E;i++){
            int v = in.readInt() - 1, w = in.readInt() - 1;
            double weight = in.readDouble();
            G.addEdge(new Edge(v, w, weight));
        }
        Stopwatch stopwatch = new Stopwatch();
        KruskalMST kruskalMST = new KruskalMST(G);
        StdOut.println(stopwatch.elapsedTime());
        StdOut.println("Edge weight cost: " + kruskalMST.weight());
        in = new In(args[0]);
        int V_prime = in.readInt(), E_prime = in.readInt();
        G = new EdgeWeightedGraph(V_prime);
        for(int i = 0;i < E;i++){
            int v = in.readInt() - 1, w = in.readInt() - 1;
            double weight = in.readDouble();
            G.addEdge(new Edge(v, w, weight));
        }
        stopwatch = new Stopwatch();
        PrimMST primMST= new PrimMST(G);
        StdOut.println(stopwatch.elapsedTime());
        StdOut.println("Edge weight cost: " + primMST.weight());
        // the exercise seems to show that PrimMST runs faster than Kruskal's
        /**
         * 0.04
         * Edge weight cost: -3612829.0
         * 0.007
         * Edge weight cost: -3612829.0
         */
    }
}
