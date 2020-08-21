public class NW {
    public static int NWAlgorithm(String X, String Y, int gapCost, int mismatchCost){
        int[][] A = new int[X.length() + 1][Y.length() + 1];
        int m = X.length(), n = Y.length();
        for(int i = 0;i < m;i++)
            A[i][0] = i;
        for(int j = 0;j < n;j++)
            A[0][j] = j;
        for(int i = 1;i <= m;i++){
            char c = X.charAt(i - 1);
            for(int j = 1;j <= n;j++){
                char t = Y.charAt(j - 1);
                int cost = 0;
                if(c != t){
                    cost += mismatchCost;
                }
                A[i][j] = Math.min(A[i - 1][j - 1] + cost, Math.min(A[i - 1][j] + gapCost, A[i][j - 1] + gapCost));
            }
        }
        return A[m][n];
    }

    public static void main(String[] args) {

    }
}
