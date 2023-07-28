import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.*;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 */
public class Main2 {

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

        public int numberOfAlternativeRoutes(List<List<Integer>> adjList) {
            int n = adjList.size();
            int[] minSteps = new int[n];
            int[] numPaths = new int[n];
            Arrays.fill(minSteps, Integer.MAX_VALUE);
            minSteps[0] = 0;
            numPaths[0] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(0);
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int next : adjList.get(curr)) {
                    if (minSteps[next] > minSteps[curr] + 1) {
                        minSteps[next] = minSteps[curr] + 1;
                        numPaths[next] = numPaths[curr];
                        queue.add(next);
                    } else if (minSteps[next] == minSteps[curr] + 1) {
                        numPaths[next] += numPaths[curr];
                    }
                }
            }
            return numPaths[n - 1] % 1000000007;
        }

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();

            List<List<Integer>> adjList = new ArrayList<>(n);

            for (int i = 0; i < n; i++)
                adjList.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                u--;
                v--;
                adjList.get(u).add(v);
                adjList.get(v).add(u);
            }
            System.out.println(numberOfAlternativeRoutes(adjList));
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