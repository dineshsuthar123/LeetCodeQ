package Daily_questions;

// _1368.java
import java.util.*;

class DijkstraSolution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[] dirX = {0, 0, 1, -1};
        int[] dirY = {1, -1, 0, 0};

        int[][] cost = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        cost[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0];
            int y = curr[1];
            int c = curr[2];

            if (x == m - 1 && y == n - 1) {
                return c;
            }

            for (int k = 0; k < 4; k++) {
                int newX = x + dirX[k];
                int newY = y + dirY[k];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    int newCost = c + (grid[x][y] == k + 1 ? 0 : 1);
                    if (newCost < cost[newX][newY]) {
                        cost[newX][newY] = newCost;
                        pq.add(new int[]{newX, newY, newCost});
                    }
                }
            }
        }
        return -1;
    }
}

class BFSSolution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] cost = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(cost[i], Integer.MAX_VALUE);
        }

        Deque<int[]> q = new ArrayDeque<>();
        q.offerFirst(new int[]{0, 0});
        cost[0][0] = 0;

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while (!q.isEmpty()) {
            int[] arr = q.pollFirst();
            int r = arr[0];
            int c = arr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + direction[i][0];
                int nc = c + direction[i][1];
                int costs = (grid[r][c] != (i + 1)) ? 1 : 0;

                if (nr >= 0 && nr < m && nc >= 0 && nc < n && cost[r][c] + costs < cost[nr][nc]) {
                    cost[nr][nc] = cost[r][c] + costs;
                    if (costs == 1) {
                        q.offerLast(new int[]{nr, nc});
                    } else {
                        q.offerFirst(new int[]{nr, nc});
                    }
                }
            }
        }
        return cost[m-1][n-1];
    }
}

public class _1368 {
    public static void main(String[] args) {
        // Test case 1
        int[][] grid1 = {
                {1,1,1,1},
                {2,2,2,2},
                {1,1,1,1},
                {2,2,2,2}
        };

        // Test case 2
        int[][] grid2 = {
                {1,1,3},
                {3,2,2},
                {1,1,4}
        };

        DijkstraSolution dijkstra = new DijkstraSolution();
        BFSSolution bfs = new BFSSolution();

        System.out.println("Test Case 1:");
        System.out.println("Dijkstra Solution: " + dijkstra.minCost(grid1));
        System.out.println("BFS Solution: " + bfs.minCost(grid1));

        System.out.println("\nTest Case 2:");
        System.out.println("Dijkstra Solution: " + dijkstra.minCost(grid2));
        System.out.println("BFS Solution: " + bfs.minCost(grid2));
    }
}