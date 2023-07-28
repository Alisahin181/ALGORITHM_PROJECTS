
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Soru2_3 {

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
    	
    	private static final int MOD = 1000000007;
    	
        public static int numberOfAlternativeRoutes(ArrayList<ArrayList<Integer>> adjList) {
            int n = adjList.size();
            int[] dist = new int[n];
            int[] pathCount = new int[n];
            Arrays.fill(dist, -1);

            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            dist[0] = 0;
            pathCount[0] = 1;

            while (!queue.isEmpty()) {
                int currentNode = queue.poll();

                for (int neighbor : adjList.get(currentNode)) {
                    if (dist[neighbor] == -1) {
                        dist[neighbor] = dist[currentNode] + 1;
                        queue.add(neighbor);
                    }

                    if (dist[neighbor] == dist[currentNode] + 1) {
                        pathCount[neighbor] += pathCount[currentNode];
                        pathCount[neighbor] %= MOD;
                    }
                }
            }

            return pathCount[n - 1];
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>(n);

            for (int i = 0; i < n; i++) {
                adjList.add(new ArrayList<Integer>());
            }

            for (int i = 0 ; i < m ; i++) {
                int u,v;
                u = in.nextInt() - 1;
                v = in.nextInt() - 1;
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }

            int result = numberOfAlternativeRoutes(adjList);
            System.out.println(result);
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