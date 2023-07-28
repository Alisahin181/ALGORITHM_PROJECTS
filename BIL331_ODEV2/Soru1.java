import java.util.Comparator;
import java.util.PriorityQueue;

/*Film yayýn platformu için çalýþýyorsunuz ve kullanýcýlarýn izleme geçmiþlerine dayanarak 
 * en iyi K filmi önermek istiyorlar. Þirket, çeþitli kullanýcýlardan gelen film derecelendirmelerine
 *  sahip büyük bir veritabanýna sahiptir ve bu verileri kullanarak en yüksek ortalama derecelendirmeye
 *   sahip en iyi k filmi bulmak istiyorlar. Film derecelendirmeleri, her bir elemaný 1 ile 5 arasýnda 
 *   bir tamsayý olan n x m tamsayý matrisi olarak verilir. n, filmlerin sayýsýný ve m, veritabanýndaki 
 *   kullanýcý sayýsýný belirtir. Algoritmanýzýn çalýþma zamaný O(n x m) olmalýdýr. Veritabanýnda tek bir 
 *   kullanýcý varsa, algoritmanýz O(n) zamanýnda çalýþmalýdýr. Bu çalýþma zamaný kýsýtlamasý altýnda filmleri 
 *   sýralayamayacaðýnýzý unutmayýn.
 */

public class Soru1 {

	public static int[] findTopMovies(int[][] ratings, int k) {
		
		int n=ratings.length; //film sayýsý
		int m=ratings[0].length; // Kullanýcý sayýsý
		
		double[] averageRates=new double[n];
		for(int i=0;i<n;i++) {
			
			int sum=0;
			for(int j=0;j<m;j++) {
				
				sum+=ratings[i][j];
				
			}
			averageRates[i] = (double) sum / m;
		}
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, new Comparator<Integer>() {
		    @Override
		    public int compare(Integer i, Integer j) {
		        return Double.compare(averageRates[i], averageRates[j]);
		    }
		});
		
		for(int i=0; i<n;i++) {
			
			if(minHeap.size()<k) {
				
				minHeap.offer(i);
				
			}else if(averageRates[i] >averageRates[minHeap.peek()] ) {
				
				minHeap.poll();
				minHeap.offer(i);
				
			}
			
		}

        int[] topMovies = new int[k];
        int i = 0;
        for (int movieIdx : minHeap) {
            topMovies[i++] = movieIdx;
        }
        
        return topMovies;
		
	}
	
}

