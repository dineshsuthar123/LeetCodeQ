package Daily_questions;

public class _2658 {
    public static void main(String[] args) {

        int[][] grid1 = {
                {0, 2, 1, 0},
                {4, 0, 0, 3},
                {1, 0, 0, 4}
        };

        int[][] grid2 = {
                {1, 0, 0},
                {0, 0, 0},
                {0, 0, 1}
        };

        MaxFishFinder solver = new MaxFishFinder();
        System.out.println("Test Case 1 Result: " + solver.findMaxFish(grid1));
        System.out.println("Test Case 2 Result: " + solver.findMaxFish(grid2));
    }
}

class MaxFishFinder {
    public int findMaxFish(int[][] grid) {
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0) continue;
                ans = Math.max(ans, dfs(grid, i, j, m, n));
            }
        }
        return ans;
    }

    private int dfs(int[][] grid, int i, int j, int m, int n){
        int sum = 0;
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) return sum;

        sum += grid[i][j];

        grid[i][j] = 0;

        sum += dfs(grid, i, j + 1, m, n);
        sum += dfs(grid, i, j - 1, m, n);
        sum += dfs(grid, i - 1, j, m, n);
        sum += dfs(grid, i + 1, j, m, n);

        return sum;
    }
}