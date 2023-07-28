import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main1 {

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

        int n, m;
        String[][] grid;
        boolean[][] visited;

        private final int[] dx = {1, -1, 0, 0};
        private final int[] dy = {0, 0, 1, -1};

        public int minRelayNodesToRemove(String[][] grid) {
            n = grid.length;
            m = grid[0].length;
            this.grid = grid;
            visited = new boolean[n][m];

            int relayNodes = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j].equals("#")) {
                        relayNodes++;
                    }
                }
            }

            if (relayNodes <= 2) {
                return -1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j].equals("#")) {
                        grid[i][j] = ".";
                        int components = countComponents();
                        grid[i][j] = "#";

                        if (components > 1) {
                            return 1;
                        }
                    }
                }
            }

            return 2;
        }

        private int countComponents() {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    visited[i][j] = false;
                }
            }

            int components = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j].equals("#") && !visited[i][j]) {
                        components++;
                        dfs(i, j);
                    }
                }
            }

            return components;
        }

        private void dfs(int x, int y) {
            visited[x][y] = true;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny].equals("#") && !visited[nx][ny]) {
                    dfs(nx, ny);
                }
            }
        }

        public void solve(InputReader in, PrintWriter out) {
            n = in.nextInt();
            m = in.nextInt();
            grid = new String[n][m];
            for (int i = 0; i < n; i++) {
                String line = in.next();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = String.valueOf(line.charAt(j));
                }
            }
            int result = minRelayNodesToRemove(grid);
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