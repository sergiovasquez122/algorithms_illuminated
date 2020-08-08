import java.math.BigInteger;

public class Karatsuba {

    public static BigInteger karatsuba(BigInteger x, BigInteger y){
        int N = Math.min(x.bitLength(), y.bitLength());
        if(N == 1) return x.multiply(y);
        return null;
    }
}
