import edu.princeton.cs.algs4.Bag;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    Digraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0;v < V; v++)
            adj[v] = new Bag<>();
    }

    int V(){
        return V;
    }

    int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        E++;
    }

    Iterable<Integer> adj(int v){
        return adj[v];
    }

    Digraph reverse(){
        Digraph R = new Digraph(V);
        for(int v = 0; v < V; v++)
            for(int w : adj(v))
                R.addEdge(w, v);
        return R;
    }
}
