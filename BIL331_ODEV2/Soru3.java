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
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        public boolean relocation(int[][] adjacencyList, int k) {
            int n = adjacencyList.length;
            int[] color = new int[n]; // 0: unvisited, 1: color 1, 2: color 2

            for (int i = 0; i < n; i++) {
                if (color[i] == 0) {
                    Set<Integer> color1 = new HashSet<>();
                    Set<Integer> color2 = new HashSet<>();
                    if (!dfs(adjacencyList, i, color, color1, color2, k)) {
                        color1.clear();
                        color2.clear();
                        if (!dfs(adjacencyList, i, color, color2, color1, k)) {
                            continue;
                        }
                    }
                    if (color1.size() > k || color2.size() > k) {
                        continue;
                    }
                    return true;
                }
            }

            return false;
        }

    	private boolean dfs(int[][] adjacencyList, int node, int[] color,
                Set<Integer> color1, Set<Integer> color2, int k) {
				color[node] = (color1.size() <= color2.size()) ? 1 : 2;
				if (color[node] == 1) {
				    color1.add(node);
				} else {
				    color2.add(node);
				}
				if (color1.size() > k || color2.size() > k) {
				    return false;
				}
				for (int neighbor : adjacencyList[node]) {
				    if (color[neighbor] == 0 && !dfs(adjacencyList, neighbor, color, color1, color2, k)) {
				        return false;
				    }
				    if (color[neighbor] == color[node]) {
				        return false;
				    }
				}
				return true;
				}
    

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int k = in.nextInt();

            int adj[][] = new int[n][];

            for (int i = 0 ; i < n; i++) {
                int nOfNeighbours = in.nextInt();
                adj[i] = new int[nOfNeighbours];
                for (int j = 0 ; j < nOfNeighbours ; j++)
                    adj[i][j] = in.nextInt();
            }
            
            if (relocation(adj,k))
                System.out.println("YES");
            else
                System.out.println("NO");
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
