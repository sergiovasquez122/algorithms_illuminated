import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;
    private double weight;

    public PrimMST(EdgeWeightedGraph G){
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        weight = 0;
        for(int v = 0;v < G.V();v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<>(G.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while(!pq.isEmpty())
            visit(G, pq.delMin());

        for(int v = 1;v < edgeTo.length; v++) {
            weight += edgeTo[v].weight();
        }
    }

    public Iterable<Edge> edges(){
        Bag<Edge> mst = new Bag<>();
        for(int v = 1;v < edgeTo.length; v++) {
            mst.add(edgeTo[v]);
        }
        return mst;
    }

    public double weight(){
        return weight;
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            int w = e.other(v);
            if(marked[w]) continue;
            if(e.weight() < distTo[w]){
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if(pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }
}
