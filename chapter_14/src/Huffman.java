import edu.princeton.cs.algs4.*;

public class Huffman {

    private static class Node implements Comparable<Node>
    {
        private int value;
        private int freq;
        private final Node left, right;

        public Node(int value, int freq, Node left, Node right) {
            this.value = value;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf(){
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node o) {
            return freq - o.freq;
        }
    }

    public static SeparateChainingHashST<Integer, String> buildCode(Node root){
        SeparateChainingHashST<Integer, String> st = new SeparateChainingHashST<>();
        buildCode(st, root, "");
        return st;
    }

    public static void buildCode(SeparateChainingHashST<Integer, String> st, Node x, String s){
        if(x.isLeaf()){
            st.put(x.value, s);
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MinPQ<Node> minPQ = new MinPQ<>(in.readInt());
        int counter = 0;
        while(!in.isEmpty()){
            int value = in.readInt();
            minPQ.insert(new Node(counter++, value, null, null)); // using the weight both as the symbol and as the frequency
        }
        while(minPQ.size() > 1){
            Node left = minPQ.delMin();
            Node right = minPQ.delMin();
            minPQ.insert(new Node(0, left.freq + right.freq, left, right));
        }
        SeparateChainingHashST<Integer, String> st = buildCode(minPQ.delMin());
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        for(Integer key : st.keys()){
            minLength = Integer.min(minLength, st.get(key).length());
            maxLength = Integer.max(maxLength, st.get(key).length());
        }
        StdOut.println("Min length encoding: " + minLength);
        StdOut.println("Max lenght encoding: " + maxLength);
    }
}
