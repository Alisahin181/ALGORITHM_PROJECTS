
public class Bil331 {
	
	public static int[] primeNumbers={1979339339,197933933,19793393};
	
	public static void main(String args[]) {
		

        System.out.println(countDNAOccurrences(args[0], args[1]));
        
		
	}
	
	public static int countDNAOccurrences(String s,String q) {
		
		int m=q.length();
		int n=s.length();
		int count=0;
		
		if(n<m)
			return 0;
		
		int[] hashQ=new int[3];
		int[] hashS=new int[3];
		
		for(int i=0;i<m;i++) {
			
			for(int j=0;j<3;j++) {
				
				hashQ[j]=(hashQ[j]*4 + map(q.charAt(i))) % (primeNumbers[j]);
				hashS[j]=(hashS[j]*4 + map(s.charAt(i))) %(primeNumbers[j]);
			}
		}
		
        if (isMatches(hashS, hashQ)) {
            count++;
        }
		
        
        
        for (int i = 1; i <= n - m; i++) {
            for (int j = 0; j < 3; j++) {
                hashS[j] = ((hashS[j] - map(s.charAt(i - 1)) * pow(4, m - 1, primeNumbers[j])) * 4
                        + map(s.charAt(i + m - 1))) % primeNumbers[j];
            }
            if (isMatches(hashS, hashQ)) {
                count++;
            }
        }
		

		return count;
	}
	
	private static int pow(int a,int b,int m) {
		
		int result=1;
		while(b>0) {
			
			if(b%2 == 1) {
				
				result=(result*a)%m;
			}
			a=(a*a)%m;
			b/=2;
		}
		return result;
	}
	
	
    public static boolean isMatches(int[] hashS, int[] hashQ) {
    	
        for (int i = 0; i < 3; i++) {
            if (hashS[i] != hashQ[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    
	public static int map(char c) {
		
		if(c=='A')
			return 0;
		else if(c=='T')
			
			return 1;
		else if(c=='G')
			
			return 2;
		else
			
			return 3;
		
	}
	
	

}
