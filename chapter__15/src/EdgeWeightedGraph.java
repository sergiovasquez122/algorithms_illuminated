import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;

public class EdgeWeightedGraph {
    private final int V;
    private int E;
    private Bag<Edge>[] adj;
    public EdgeWeightedGraph(int v){
        V = v;
        E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int i = 0;i < V;i++)
            adj[i] = new Bag<>();
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    Iterable<Edge> adj(int v){
        return adj[v];
    }

    Iterable<Edge> edges(){
        Queue<Edge> edges = new Queue<>();
        for(int i = 0;i < V;i++)
            for(Edge e : adj(i))
                edges.enqueue(e);
        return edges;
    }
}
