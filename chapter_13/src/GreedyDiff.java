public class GreedyDiff {

    private class Node implements Comparable<Node> {
        private int weight;
        private int length;
        public Node(int weight, int length){
            this.weight = weight;
            this.length = length;
        }

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }
}
