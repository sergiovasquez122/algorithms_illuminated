public class MinPQ <Key extends Comparable<Key>>{
    private Key[] pq;
    private int N;

    MinPQ(int max){
        pq = (Key[]) new Comparable[max + 1];
        N = 0;
    }

    void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    Key peek(){
        return pq[1];
    }

    Key delMax(){
        Key key = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return key;
    }

    boolean isEmpty(){
        return N == 0;
    }

    int size(){
        return N;
    }

    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void swim(int k){
        while(k > 1 && less(k / 2, k)){
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k){
        while(2 * k <= N){
            int idx = 2 * k;
            if(idx + 1 < N && less(idx, idx + 1)) idx++;
            if(!less(k, idx)) break;
            exch(k, idx);
            k = idx;
        }
    }

    public static void main(String[] args) {
        MinPQ<Integer> minPq = new MinPQ<>(32);

        minPq.insert(70);
        minPq.insert(40);
        minPq.insert(50);
        minPq.insert(20);
        minPq.insert(60);
        minPq.insert(100);
        minPq.insert(80);
        minPq.insert(30);
        minPq.insert(10);
        minPq.insert(90);

        while(!minPq.isEmpty()){
            System.out.println(minPq.delMax());
        }
    }
}