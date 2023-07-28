import java.util.Comparator;
import java.util.PriorityQueue;

/*Film yay�n platformu i�in �al���yorsunuz ve kullan�c�lar�n izleme ge�mi�lerine dayanarak 
 * en iyi K filmi �nermek istiyorlar. �irket, �e�itli kullan�c�lardan gelen film derecelendirmelerine
 *  sahip b�y�k bir veritaban�na sahiptir ve bu verileri kullanarak en y�ksek ortalama derecelendirmeye
 *   sahip en iyi k filmi bulmak istiyorlar. Film derecelendirmeleri, her bir eleman� 1 ile 5 aras�nda 
 *   bir tamsay� olan n x m tamsay� matrisi olarak verilir. n, filmlerin say�s�n� ve m, veritaban�ndaki 
 *   kullan�c� say�s�n� belirtir. Algoritman�z�n �al��ma zaman� O(n x m) olmal�d�r. Veritaban�nda tek bir 
 *   kullan�c� varsa, algoritman�z O(n) zaman�nda �al��mal�d�r. Bu �al��ma zaman� k�s�tlamas� alt�nda filmleri 
 *   s�ralayamayaca��n�z� unutmay�n.
 */

public class Soru1 {

	public static int[] findTopMovies(int[][] ratings, int k) {
		
		int n=ratings.length; //film say�s�
		int m=ratings[0].length; // Kullan�c� say�s�
		
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

