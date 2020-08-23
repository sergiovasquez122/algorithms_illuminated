import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BellmanFordSP {
    private double [] distTo;
    private boolean hasNegativeCycle = false;

    public BellmanFordSP(EdgeWeightedDigraph G, int s){
        distTo = new double[G.V()];

        for(int i = 0;i < G.V();i++)
            distTo[i] = Double.POSITIVE_INFINITY;

        distTo[s] = 0;
        for(int pass = 0;pass < G.V();pass++) {
            for (int v = 0; v < G.V(); v++) {
                for (DirectedEdge e : G.adj(v)) {
                    int from = e.from(), to = e.to();
                    if(distTo[from] != Double.POSITIVE_INFINITY && distTo[to] > distTo[from] + e.weight()){
                        distTo[to] = distTo[from] + e.weight();
                    }
                }
            }
        }

        for (int v = 0; v < G.V(); v++) {
            for (DirectedEdge e : G.adj(v)) {
                int from = e.from(), to = e.to();
                if(distTo[from] != Double.POSITIVE_INFINITY && distTo[to] > distTo[from] + e.weight()){
                    distTo[to] = distTo[from] + e.weight();
                    hasNegativeCycle = true;
                }
            }
        }
    }

    public double distTo(int v){
       return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public boolean hasNegativeCycle(){
        return hasNegativeCycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int V = in.readInt(), E = in.readInt();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
        while(in.hasNextLine()){
            int u = in.readInt() - 1, v = in.readInt() - 1, weight = in.readInt();
            G.addEdge(new DirectedEdge(u, v, weight));
        }
        BellmanFordSP[] shortestPaths = new BellmanFordSP[G.V()];
        for(int i  = 0;i < G.V();i++)
            shortestPaths[i] = new BellmanFordSP(G, i);

        double shortestDistance = Double.POSITIVE_INFINITY;
        boolean hasNegativeCycle = false;
        for(int i = 0;i < shortestPaths.length && !hasNegativeCycle;i++) {
            for (int v = 0; v < G.V(); v++) {
                hasNegativeCycle = shortestPaths[i].hasNegativeCycle();
                if(v != i) shortestDistance = Double.min(shortestDistance, shortestPaths[i].distTo(v));
            }
        }
        StdOut.println("Has negative cycle: " + hasNegativeCycle);
        StdOut.println("Shortest shortest path cost: " + shortestDistance);
    }
}
