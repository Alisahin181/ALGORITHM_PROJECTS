
import java.util.Random;

public class DNA {
    private static final int BASE = 4;
    private static final int[] PRIMES = { 1000000007, 1000000009, 1000000021 };
    private static final Random random = new Random();

    public static int countDNAOccurrences(String s, String q) {
        int n = s.length(), m = q.length();
        if (n < m) {
            return 0;
        }

        int count = 0;
        int[] hashQ = new int[3];
        int[] hashS = new int[3];

        // Compute hash values of q
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                hashQ[j] = (hashQ[j] * BASE + toInt(q.charAt(i))) % PRIMES[j];
            }
        }

        // Compute hash values of the first m characters of s
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < 3; j++) {
                hashS[j] = (hashS[j] * BASE + toInt(s.charAt(i))) % PRIMES[j];
            }
        }

        // Compare hash values of q and the first m characters of s
        if (hashMatches(hashS, hashQ)) {
            count++;
        }

        // Compute hash values of remaining substrings of s
        for (int i = 1; i <= n - m; i++) {
            for (int j = 0; j < 3; j++) {
                hashS[j] = ((hashS[j] - toInt(s.charAt(i - 1)) * pow(BASE, m - 1, PRIMES[j])) * BASE
                        + toInt(s.charAt(i + m - 1))) % PRIMES[j];
            }
            if (hashMatches(hashS, hashQ)) {
                count++;
            }
        }

        return count;
    }

    private static boolean hashMatches(int[] hashS, int[] hashQ) {
        for (int i = 0; i < 3; i++) {
            if (hashS[i] != hashQ[i]) {
                return false;
            }
        }
        return true;
    }

    private static int toInt(char c) {
        switch (c) {
        case 'A':
            return 0;
        case 'C':
            return 1;
        case 'G':
            return 2;
        case 'T':
            return 3;
        }
        throw new IllegalArgumentException("Invalid nucleotide: " + c);
    }

    private static int pow(int a, int b, int m) {
        int result = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % m;
            }
            a = (a * a) % m;
            b /= 2;
        }
        return result;
    }

}