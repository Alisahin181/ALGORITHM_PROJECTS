

public class UzunYol{
	
	public static void main(String args[]) {
		
		System.out.println(countDNAOccurrences("ATATAT","AT"));
		
	}
	
	public static int countDNAOccurrences(String s, String q) {
	    int n = s.length();
	    int m = q.length();
	    int count = 0;
	    int i = 0;

	    // Choose three prime numbers for hashing
	    int p1 = 1000000007;
	    int p2 = 1000000009;
	    int p3 = 1000000021;

	    // Compute the powers of 4
	    int pow1 = 1;
	    int pow2 = 1;
	    int pow3 = 1;
	    for (int j = 0; j < m - 1; j++) {
	        pow1 = (pow1 * 4) % p1;
	        pow2 = (pow2 * 4) % p2;
	        pow3 = (pow3 * 4) % p3;
	    }

	    // Compute the hash values of q and the first m characters of s
	    int hashq1 = 0;
	    int hashq2 = 0;
	    int hashq3 = 0;
	    int hashs1 = 0;
	    int hashs2 = 0;
	    int hashs3 = 0;
	    for (int j = 0; j < m; j++) {
	        hashq1 = (hashq1 * 4 + mapChar(q.charAt(j))) % p1;
	        hashq2 = (hashq2 * 4 + mapChar(q.charAt(j))) % p2;
	        hashq3 = (hashq3 * 4 + mapChar(q.charAt(j))) % p3;
	        hashs1 = (hashs1 * 4 + mapChar(s.charAt(j))) % p1;
	        hashs2 = (hashs2 * 4 + mapChar(s.charAt(j))) % p2;
	        hashs3 = (hashs3 * 4 + mapChar(s.charAt(j))) % p3;
	    }

	    // Compare the hash values of q and each substring of s of length m
	    while (i <= n - m) {
	        if (hashq1 == hashs1 && hashq2 == hashs2 && hashq3 == hashs3) {
	            boolean match = true;
	            for (int j = 0; j < m; j++) {
	                if (q.charAt(j) != s.charAt(i + j)) {
	                    match = false;
	                    break;
	                }
	            }
	            if (match) {
	                count++;
	            }
	        }

	        // Update the hash value of the next substring of s
	        if (i < n - m) {
	            hashs1 = ((hashs1 - mapChar(s.charAt(i)) * pow1) * 4 + mapChar(s.charAt(i + m))) % p1;
	            hashs2 = ((hashs2 - mapChar(s.charAt(i)) * pow2) * 4 + mapChar(s.charAt(i + m))) % p2;
	            hashs3 = ((hashs3 - mapChar(s.charAt(i)) * pow3) * 4 + mapChar(s.charAt(i + m))) % p3;
	            i++;
	        } else {
	            break;
	        }
	    }

	    return count;
	}

	// Helper method to map a character to an integer between 0 and 3
	private static int mapChar(char c) {
	    switch (c) {
	        case 'A':
	            return 0;
	        case 'C':
	            return 1;
	        case 'G':
	            return 2;
	        default:
	        	return 3;
	    }
	 }
	 
	
}

