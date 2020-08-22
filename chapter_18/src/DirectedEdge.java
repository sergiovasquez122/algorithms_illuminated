public class DirectedEdge {
    private final int v;
    private final int w;
    private final int weight;

    public DirectedEdge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public int weight(){
        return weight;
    }
}
