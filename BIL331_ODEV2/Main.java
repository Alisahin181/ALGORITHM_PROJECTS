import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

/*Sen ve arkadaşın, kayıp bir antik medeniyetin hazinesini bulmak için bir görevdesiniz. 
Hazinenin yerini gösteren bir haritanız var, ancak labirent gibi bir mağara sistemindedir
ve birçok dönemeçe sahiptir. Ne yazık ki, harita çok detaylı değildir ve hazineye doğru 
olan doğru yolu göstermez.

Dahası, mağara sistemi tuzaklar, tehlikeli yaratıklar ve çıkmazlarla doludur. Amacınız,
 hazineleri bulmak ve mağara sistemine güvenli bir şekilde geri dönmektir.

Harita, n × m String matrisi olarak temsil edilir, burada her karakter üç değer alabilir:
".", "#", "t". Güvenli konumlar ".", duvar ve tuzaklar "#" ile gösterilir ve hazineler "t"
ile gösterilir.

İndeksleri koordinatlar olarak kullanabilirsiniz. Örneğin, sol üst köşe (0,0) ile alt sağ köşe
n − 1, m − 1 arasındadır. Her adımda, seçtiğiniz yöne göre herhangi bir dört yönden (sol, sağ,
yukarı veya aşağı) hareket edebilirsiniz. Bir harekette, yolunuzda duvar veya tuzak yoksa 
mevcut konumunuzdan 1 veya 2 birim seyahat edebilirsiniz. Yolculuğunuz "." konumları arasından
geçer.

Hazineyi güvenli bir şekilde bulmanıza yardımcı olacak bir algoritma yazın. Algoritmanız
harita olarak girilecek ve en az sayıda hamle ile hazineye giden yol olarak çıktı verecektir.
Yolculuğunuz (0,0) noktasında başlar ve burası güvenli bir konum olduğu garanti edilir. 
Algoritmanızın çıktısı, her satırı yolunuzdaki güvenli bir konum olan bir tamsayı matrisi olmalıdır.
Çıktınızın ilk satırı (0,0) ile ve son satırı hazine konumu ile eşleşmelidir.

Java'da, public int[][] findPathToTreasure(String[][] map) imzasına sahip olan yöntemi tamamlayın. 
Algoritmanızın çalışma süresi O(n ·m) olmalıdır.

 * */

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.Comparator;
import java.io.InputStream;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */



public class Main {
	


    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(in, out);
        out.close();
    }
 
    static class TaskA {

    	public int[][] findPathToTreasure(String[][] map) {
    	    int n = map.length;
    	    int m = map[0].length;
    	    List<int[]> path = new ArrayList<>();
    	    boolean[][] visited = new boolean[n][m];
    	    dfs(0, 0, map, visited, path);
    	    int[][] result = new int[path.size()][2];
    	    for (int i = 0; i < path.size(); i++) {
    	        result[i][0] = path.get(i)[0];
    	        result[i][1] = path.get(i)[1];
    	    }
    	    return result;
    	}

    	private boolean dfs(int x, int y, String[][] map, boolean[][] visited, List<int[]> path) {
    	    int n = map.length;
    	    int m = map[0].length;
    	    if (map[x][y].equals("t")) {
    	        path.add(new int[]{x, y});
    	        return true;
    	    }
    	    visited[x][y] = true;
    	    int[][] moves = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
    	    for (int[] move : moves) {
    	        int nx = x + move[0];
    	        int ny = y + move[1];
    	        if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny].equals(".") && !visited[nx][ny]) {
    	            path.add(new int[]{nx, ny});
    	            if (dfs(nx, ny, map, visited, path)) {
    	                return true;
    	            }
    	            path.remove(path.size() - 1);
    	        }
    	    }
    	    return false;
    	}

    	

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            String mat[][] = new String[n][m];
            for (int i = 0 ; i < n ; i++) {
        		String line = in.next();
		        for (int j = 0; j < m ; j++)
	        		mat[i][j] = String.valueOf(line.charAt(j));
	    	}

	        int[][] path = findPathToTreasure(mat);

	        System.out.println(path.length);
	        for (int[] node : path)
	            System.out.println(node[0] + " " + node[1]);           
        }

    }
 
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;
 
        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }
 
        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

 
        public int nextInt() {
            return Integer.parseInt(next());
        }
 
        public long nextLong() {
            return Long.parseLong(next());
        }
 

    }
}

