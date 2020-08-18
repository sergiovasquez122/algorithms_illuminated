import edu.princeton.cs.algs4.*;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;

    public KruskalMST(EdgeWeightedGraph G){
        mst = new Queue<>();
        weight = 0;
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for(Edge e : G.edges()){
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while(!pq.isEmpty() && mst.size() < G.V() - 1){
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if(uf.connected(v, w)) continue;
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int V = in.readInt(), E = in.readInt();
        EdgeWeightedGraph G = new EdgeWeightedGraph(V);
        for(int i = 0;i < E;i++){
            int v = in.readInt() - 1, w = in.readInt() - 1;
            double weight = in.readDouble();
            G.addEdge(new Edge(v, w, weight));
        }
        KruskalMST kruskalMST = new KruskalMST(G);
        StdOut.println("Edge weight cost: " + kruskalMST.weight());
    }

}
