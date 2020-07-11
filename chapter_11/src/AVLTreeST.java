import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class AVLTreeST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node{
        private Node left;
        private Node right;
        private final Key key;
        private Value val;
        private int size;
        private int height;

        public Node(Key key, Value val, int size, int height){
            this.key = key;
            this.val = val;
            this.size = size;
            this.height = height;
        }
    }

    public Value get(Key key){
        Node x = get(root, key);
        if(x == null) return null;
        return x.val;
    }

    private Node get(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return get(x.left, key);
        else if(cmp > 0) return get(x.right, key);
        else return x;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    private Node insert(Node x, Key key, Value val){
        if(x == null) return new Node(key,val, 1, -1);
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = insert(x.left, key, val);
        else if(cmp > 0) x.right = insert(x.right, key, val);
        else {x.val = val;return x;}
        x.size = size(x.left) + size(x.right) + 1;
        x.height = 1 + Integer.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void put(Key key, Value val){
        root = insert(root, key, val);
    }

    public void delete(Key key){
        root = delete(root, key);
    }

    private Node delete(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) x.left = delete(x.left, key);
        else if(cmp > 0) x.right = delete(x.right, key);
        else {
            if(x.left == null) return x.right;
            if(x.right == null) return x.left;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        x.height = 1 + Integer.max(height(x.left), height(x.right));
        return balance(x);
    }

    public int height(){
        return height(root);
    }

    private int height(Node x){
        if(x == null) return -1;
        else return x.height;
    }

    public int size(){
        return size(root);
    }

    private int size(Node x){
        if(x == null) return 0;
        else return x.size;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if(x.left != null) return min(x.left);
        else return x;
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if(x.right != null) return max(x.right);
        else return x;
    }

    public boolean isEmpty(){
        return root == null;
    }

    private Node deleteMin(Node x){
        if(x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Integer.max(height(x.left), height(x.right));
        return balance(x);
    }

    private Node deleteMax(Node x){
        if(x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Integer.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    public void deleteMax(){
        root = deleteMax(root);
    }

    public Iterable<Key> keys(){
        return inorder();
    }


    public Iterable<Key> inorder(){
        Queue<Key> queue = new Queue<>();
        inorder(root, queue);
        return queue;
    }

    private void inorder(Node x, Queue<Key> queue){
        if(x == null) return;
        inorder(x.left, queue);
        queue.enqueue(x.key);
        inorder(x.right, queue);
    }

    public Iterable<Key> levelOrder(){
        Queue<Key> levelOrder = new Queue<>();
        if(!isEmpty()){
            Queue<Node> traversal = new Queue<>();
            traversal.enqueue(root);
            while(!traversal.isEmpty()){
                Node x = traversal.dequeue();
                levelOrder.enqueue(x.key);
                if(x.left != null) traversal.enqueue(x.left);
                if(x.right != null) traversal.enqueue(x.right);
            }
        }
        return levelOrder;
    }

    public Key select(int k){
        return select(root, k).key;
    }

    public int rank(Key key){
        return rank(root, key);
    }

    private int rank(Node x, Key key){
        if(x == null) return 0;
        int cmp = key.compareTo(x.key);
        if(cmp < 0) return rank(x.left, key);
        else if(cmp > 0) return 1 + size(x.left) + rank(x.right, key);
        else return size(x.left);
    }

    private Node select(Node x, int k){
        if(x == null) return null;
        int t = size(x.left);
        if(t > k) return select(x.left, k);
        else if(t < k) return select(x.right, k - t - 1);
        else return x;
    }

    public Key ceiling(Key key){
        Node x = ceiling(root, key);
        if(x == null) return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp > 0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if(t != null) return t;
        return x;
    }

    public Key floor(Key key){
        Node x = floor(root, key);
        if(x == null) return null;
        else return x.key;
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new Queue<>();
        keys(root, lo, hi, queue);
        return queue;
    }

    private void keys(Node x, Key lo, Key hi, Queue<Key> queue){
        if(x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if(cmplo < 0) keys(x.left, lo, hi, queue);
        if(cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if(cmphi > 0) keys(x.right, lo, hi, queue);
    }

    private Node floor(Node x, Key key){
        if(x == null) return null;
        int cmp = key.compareTo(x.key);
        if(cmp == 0) return x;
        if(cmp < 0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if(t != null) return t;
        return x;
    }

    private Node leftRotate(Node x){
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public Node balance(Node x){
        if(balanceFactor(x) < -1){
            if(balanceFactor(x.right) > 0){
                x.right = rightRotate(x.right);
            }
            x = leftRotate(x);
        } else if(balanceFactor(x) > 1){
            if(balanceFactor(x.left) < 0){
                x.left = leftRotate(x.left);
            }
            x = rightRotate(x);
        }
        return x;
    }

    private int balanceFactor(Node x){
        return height(x.left) - height(x.right);
    }

    private Node rightRotate(Node x){
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        x.size = size(x.left) + size(x.right) + 1;
        y.size = size(y.left) + size(y.right) + 1;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }
}
