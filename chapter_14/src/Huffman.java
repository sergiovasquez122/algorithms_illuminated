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
            return Integer.compare(freq, o.freq);
        }
    }
}
