package Daily_questions;

import java.util.*;

public class _2661 {
    public static void main(String[] args) {
        int[] arr1 = {1,3,4,2};
        int[][] mat1 = {{1,4},{2,3}};

        int[] arr2 = {2,8,7,4,1,3,5,6,9};
        int[][] mat2 = {{3,2,5},{1,4,6},{8,7,9}};

        OptimizedSolution1 s1 = new OptimizedSolution1();
        CountdownSolution s2 = new CountdownSolution();

        System.out.println("Test case 1:");
        System.out.println("Optimized Solution: " + s1.firstCompleteIndex(arr1, mat1));
        System.out.println("Countdown Solution: " + s2.firstCompleteIndex(arr1, mat1));

        System.out.println("\nTest case 2:");
        System.out.println("Optimized Solution: " + s1.firstCompleteIndex(arr2, mat2));
        System.out.println("Countdown Solution: " + s2.firstCompleteIndex(arr2, mat2));
    }
}

class OptimizedSolution1 {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] pos = new int[arr.length + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pos[mat[i][j]] = i * n + j;
            }
        }

        int[] rows = new int[m];
        int[] cols = new int[n];

        for (int i = 0; i < arr.length; i++) {
            int position = pos[arr[i]];
            int row = position / n;
            int col = position % n;

            rows[row]++;
            cols[col]++;

            if (rows[row] == n || cols[col] == m) {
                return i;
            }
        }

        return arr.length - 1;
    }
}

class CountdownSolution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        Map<Integer, int[]> positionMap = new HashMap<>();
        int[] rowCount = new int[rows];
        int[] colCount = new int[cols];
        Arrays.fill(rowCount, cols);
        Arrays.fill(colCount, rows);
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < cols; ++c) {
                positionMap.put(mat[r][c], new int[]{r, c});
            }
        }
        for (int idx = 0; idx < arr.length; ++idx) {
            int[] pos = positionMap.get(arr[idx]);
            if (--rowCount[pos[0]] == 0 || --colCount[pos[1]] == 0) {
                return idx;
            }
        }
        return -1;
    }
}