import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.math.BigInteger;

public class Karatsuba {

    public static BigInteger karatsuba(String x, String y){
        int length = Math.max(x.length(), y.length());
        if(length == 1){
            return new BigInteger(x).multiply(new BigInteger(y));
        }
        String a = x.substring(0, length / 2);
        String b = x.substring(length / 2);
        String c = y.substring(0, length / 2);
        String d = y.substring(length / 2);
        BigInteger p = new BigInteger(a).add(new BigInteger(b));
        BigInteger q = new BigInteger(c).add(new BigInteger(d));
        BigInteger ac = karatsuba(a, c);
        BigInteger bd = karatsuba(b, d);
        BigInteger pq = p.multiply(q);
        BigInteger adbc = pq.subtract(ac).subtract(bd);
        BigInteger ten = new BigInteger("10");
        BigInteger lhs = ten.pow(length);
        BigInteger mid = ten.pow(length / 2);
        lhs = lhs.multiply(ac);
        mid = mid.multiply(adbc);
        return lhs.add(mid.add(bd));
    }

    public static void main(String[] args) {
        String x = "3141592653589793238462643383279502884197169399375105820974944592";
        String y = "2718281828459045235360287471352662497757247093699959574966967627";
        StdOut.println(karatsuba(x, y));
    }
}
