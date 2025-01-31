package Daily_questions;

import java.util.*;

public class _827 {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 0},
                {0, 1}
        };

        Solution1_827 solution1 = new Solution1_827();
        Solution2_827 solution2 = new Solution2_827();

        System.out.println("Solution 1 Result: " + solution1.largestIsland(grid));
        System.out.println("Solution 2 Result: " + solution2.largestIsland(grid));
    }
}

class Solution1_827 {
    private int n;
    private int[][] grid;
    private Map<Integer, Integer> islandSizes = new HashMap<>();
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public int largestIsland(int[][] grid) {
        this.n = grid.length;
        this.grid = grid;
        int color = 2;
        int maxIsland = 0;
        boolean hasZero = false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(i, j, color);
                    islandSizes.put(color, size);
                    maxIsland = Math.max(maxIsland, size);
                    color++;
                } else {
                    hasZero = true;
                }
            }
        }

        if (!hasZero) return maxIsland;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> visitedIslands = new HashSet<>();
                    int newSize = 1;

                    for (int[] dir : DIRECTIONS) {
                        int x = i + dir[0], y = j + dir[1];
                        if (isValid(x, y) && grid[x][y] > 1 && visitedIslands.add(grid[x][y])) {
                            newSize += islandSizes.get(grid[x][y]);
                        }
                    }

                    maxIsland = Math.max(maxIsland, newSize);
                }
            }
        }

        return maxIsland;
    }

    private int dfs(int i, int j, int color) {
        if (!isValid(i, j) || grid[i][j] != 1) return 0;

        grid[i][j] = color;
        int size = 1;

        for (int[] dir : DIRECTIONS) {
            size += dfs(i + dir[0], j + dir[1], color);
        }

        return size;
    }

    private boolean isValid(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
}

class Solution2_827 {
    private static final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        List<Integer> key = new ArrayList<>();
        int id = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, id);
                    key.add(size);
                    id++;
                }
            }
        }
        if (key.isEmpty()) return 1;

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int sum = 1;
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] >= 2) {
                            int islandId = grid[ni][nj];
                            if (!seen.contains(islandId)) {
                                sum += key.get(islandId - 2);
                                seen.add(islandId);
                            }
                        }
                    }
                    max = Math.max(max, sum);
                }
            }
        }
        return max == 0 ? n * n : max;
    }

    private int dfs(int[][] grid, int i, int j, int id) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = id;
        int count = 1;
        for (int[] dir : dirs) {
            count += dfs(grid, i + dir[0], j + dir[1], id);
        }
        return count;
    }
}