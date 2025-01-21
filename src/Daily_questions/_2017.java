package Daily_questions;

class _2017 {
    public static void main(String[] args) {
        int[][] grid1 = {{2,5,4}, {1,5,1}};
        int[][] grid2 = {{3,3,1}, {8,5,2}};
        int[][] grid3 = {{1,3,1,15}, {1,3,3,1}};

        GridGameSolution1 sol1 = new GridGameSolution1();
        GridGameSolution2 sol2 = new GridGameSolution2();

        System.out.println(sol1.gridGame(grid1) + " " + sol2.gridGame(grid1));
        System.out.println(sol1.gridGame(grid2) + " " + sol2.gridGame(grid2));
        System.out.println(sol1.gridGame(grid3) + " " + sol2.gridGame(grid3));
    }
}

class GridGameSolution1 {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long[] prefixSum1 = new long[n + 1];
        long[] prefixSum2 = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum1[i + 1] = prefixSum1[i] + grid[0][i];
            prefixSum2[i + 1] = prefixSum2[i] + grid[1][i];
        }

        long minSecondRobotPoints = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            long remainingTop = prefixSum1[n] - prefixSum1[i + 1];
            long remainingBottom = prefixSum2[i];
            long secondRobotPoints = Math.max(remainingTop, remainingBottom);
            minSecondRobotPoints = Math.min(minSecondRobotPoints, secondRobotPoints);
        }

        return minSecondRobotPoints;
    }
}

class GridGameSolution2 {
    public long gridGame(int[][] grid) {
        long top = 0;
        for (int i = 0; i < grid[0].length; i++) {
            top += grid[0][i];
        }

        long bottom = 0, res = Long.MAX_VALUE;
        for (int i = 0; i < grid[0].length; i++) {
            top -= grid[0][i];
            res = Math.min(res, Math.max(top, bottom));
            bottom += grid[1][i];
        }

        return res;
    }
}