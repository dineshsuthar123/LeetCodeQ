package Daily_questions;

import java.util.*;

public class _1765 {
    public static void main(String[] args) {
        // Test cases
        int[][] isWater1 = {{0,1}, {0,0}};
        int[][] isWater2 = {{0,0,1}, {1,0,0}, {0,0,0}};

        PeakFinderBFS solution1 = new PeakFinderBFS();
        PeakFinderOptimized solution2 = new PeakFinderOptimized();

        System.out.println("Solution 1 Results:");
        printResult(solution1.highestPeak(isWater1));
        printResult(solution1.highestPeak(isWater2));

        System.out.println("\nSolution 2 Results:");
        printResult(solution2.highestPeak(isWater1));
        printResult(solution2.highestPeak(isWater2));
    }

    private static void printResult(int[][] result) {
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class PeakFinderBFS {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;
        int[][] heights = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    heights[i][j] = 0;
                    queue.offer(new int[]{i, j});
                } else {
                    heights[i][j] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            for (int[] dir : DIRECTIONS) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (isValid(newRow, newCol, m, n) && heights[newRow][newCol] == -1) {
                    heights[newRow][newCol] = heights[row][col] + 1;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }

        return heights;
    }

    private boolean isValid(int row, int col, int m, int n) {
        return row >= 0 && row < m && col >= 0 && col < n;
    }
}

class PeakFinderOptimized {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length, n = isWater[0].length;
        int[][] result = new int[m][n];
        Deque<int[]> dq = new ArrayDeque<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (isWater[i][j] == 1) {
                    dq.offer(new int[]{i, j});
                    result[i][j] = 0;
                } else {
                    result[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        while (!dq.isEmpty()) {
            int[] curr = dq.poll();
            int r = curr[0], c = curr[1];
            int val = result[r][c];

            if (r > 0 && result[r-1][c] > val + 1) {
                result[r-1][c] = val + 1;
                dq.offer(new int[]{r-1, c});
            }
            if (r < m-1 && result[r+1][c] > val + 1) {
                result[r+1][c] = val + 1;
                dq.offer(new int[]{r+1, c});
            }
            if (c > 0 && result[r][c-1] > val + 1) {
                result[r][c-1] = val + 1;
                dq.offer(new int[]{r, c-1});
            }
            if (c < n-1 && result[r][c+1] > val + 1) {
                result[r][c+1] = val + 1;
                dq.offer(new int[]{r, c+1});
            }
        }
        return result;
    }
}