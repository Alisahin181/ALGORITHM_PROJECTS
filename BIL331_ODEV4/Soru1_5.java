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
public class Soru1_5 {

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

        private boolean[][] visited;
        private int[][] ids;
        private int[][] low;
        private boolean[][] isArt;
        private int n, m;
        private int id;
        private String[][] grid;

        private final int[] dx = {1, -1, 0, 0};
        private final int[] dy = {0, 0, 1, -1};

        public int relayNodes(String[][] grid) {
            n = grid.length;
            m = grid[0].length;
            this.grid = grid;
            visited = new boolean[n][m];
            ids = new int[n][m];
            low = new int[n][m];
            isArt = new boolean[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j].equals("#") && !visited[i][j]) {
                        id = 0;
                        dfs(i, j, -1, -1);
                    }
                }
            }

            int connectedComponents = 0;
            boolean[][] visitedComponents = new boolean[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (!visitedComponents[i][j] && grid[i][j].equals("#")) {
                        connectedComponents++;
                        dfsComponents(i, j, visitedComponents);
                    }
                }
            }

            return Math.max(0, connectedComponents - 1);
        }

        private void dfsComponents(int x, int y, boolean[][] visitedComponents) {
            visitedComponents[x][y] = true;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visitedComponents[nx][ny] && grid[nx][ny].equals("#")) {
                    dfsComponents(nx, ny, visitedComponents);
                }
            }
        }
        private void dfs(int x, int y, int parentX, int parentY) {
            visited[x][y] = true;
            low[x][y] = id;
            ids[x][y] = id;
            id++;
            int children = 0;
            boolean isArticulation = false;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny].equals("#")) {
                    if (!visited[nx][ny]) {
                        dfs(nx, ny, x, y);
                        children++;

                        if (low[nx][ny] >= ids[x][y]) {
                            isArticulation = true;
                        }
                        low[x][y] = Math.min(low[x][y], low[nx][ny]);
                    } else if (nx != parentX || ny != parentY) {
                        low[x][y] = Math.min(low[x][y], ids[nx][ny]);
                    }
                }
            }

            if ((parentX == x && parentY == y && children > 1) || (parentX != x || parentY != y && isArticulation)) {
                isArt[x][y] = true;
            }
        }
    	
        
        
        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            String grid[][] = new String[n][m];
            int count = 0;
            for (int i = 0 ; i < n ; i++) {
        		String line = in.next();
		        for (int j = 0; j < m ; j++) {
                    if (line.charAt(j) == '#')
                        count++;
                    grid[i][j] = String.valueOf(line.charAt(j));
                }
            }
            if (count <= 2) {
                System.out.println(-1);
                return;
            }
	        int result = relayNodes(grid);
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