package Daily_questions;

import java.util.PriorityQueue;

public class _407 {
    public static void main(String[] args) {
        int[][] test1 = {
                {1,4,3,1,3,2},
                {3,2,1,3,2,4},
                {2,3,3,2,3,1}
        };

        int[][] test2 = {
                {3,3,3,3,3},
                {3,2,2,2,3},
                {3,2,1,2,3},
                {3,2,2,2,3},
                {3,3,3,3,3}
        };

        TrappingWaterPQ solution1 = new TrappingWaterPQ();
        TrappingWaterOptimized solution2 = new TrappingWaterOptimized();

        System.out.println("Testing Solution 1 (Priority Queue):");
        System.out.println("Test case 1: " + solution1.trapRainWater(test1));  // Expected: 4
        System.out.println("Test case 2: " + solution1.trapRainWater(test2));  // Expected: 10

        System.out.println("\nTesting Solution 2 (Optimized):");
        System.out.println("Test case 1: " + solution2.trapRainWater(test1));  // Expected: 4
        System.out.println("Test case 2: " + solution2.trapRainWater(test2));  // Expected: 10
    }
}

class TrappingWaterPQ {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }

        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, 0, heightMap[i][0]});
            pq.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }

        for (int j = 0; j < n; j++) {
            pq.offer(new int[]{0, j, heightMap[0][j]});
            pq.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int waterTrapped = 0;

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int x = cell[0], y = cell[1], height = cell[2];

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    waterTrapped += Math.max(0, height - heightMap[nx][ny]);
                    pq.offer(new int[]{nx, ny, Math.max(height, heightMap[nx][ny])});
                }
            }
        }

        return waterTrapped;
    }
}

class TrappingWaterOptimized {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 3 || heightMap[0].length < 3) return 0;

        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] heap = new int[m * n * 3];
        int size = 0;

        for (int i = 0; i < m; i++) {
            add(heap, size++, i, 0, heightMap[i][0]);
            add(heap, size++, i, n-1, heightMap[i][n-1]);
            visited[i][0] = visited[i][n-1] = true;
        }

        for (int j = 1; j < n-1; j++) {
            add(heap, size++, 0, j, heightMap[0][j]);
            add(heap, size++, m-1, j, heightMap[m-1][j]);
            visited[0][j] = visited[m-1][j] = true;
        }

        int water = 0;
        while (size > 0) {
            int[] curr = poll(heap, --size);
            int row = curr[0], col = curr[1], h = curr[2];

            if (row > 0 && !visited[row-1][col]) {
                water += Math.max(0, h - heightMap[row-1][col]);
                visited[row-1][col] = true;
                add(heap, size++, row-1, col, Math.max(heightMap[row-1][col], h));
            }
            if (col > 0 && !visited[row][col-1]) {
                water += Math.max(0, h - heightMap[row][col-1]);
                visited[row][col-1] = true;
                add(heap, size++, row, col-1, Math.max(heightMap[row][col-1], h));
            }
            if (row < m-1 && !visited[row+1][col]) {
                water += Math.max(0, h - heightMap[row+1][col]);
                visited[row+1][col] = true;
                add(heap, size++, row+1, col, Math.max(heightMap[row+1][col], h));
            }
            if (col < n-1 && !visited[row][col+1]) {
                water += Math.max(0, h - heightMap[row][col+1]);
                visited[row][col+1] = true;
                add(heap, size++, row, col+1, Math.max(heightMap[row][col+1], h));
            }
        }
        return water;
    }

    private void add(int[] heap, int size, int row, int col, int height) {
        heap[size*3] = row;
        heap[size*3+1] = col;
        heap[size*3+2] = height;
        siftUp(heap, size);
    }

    private int[] poll(int[] heap, int size) {
        int[] result = new int[]{heap[0], heap[1], heap[2]};
        heap[0] = heap[size*3];
        heap[1] = heap[size*3+1];
        heap[2] = heap[size*3+2];
        siftDown(heap, size, 0);
        return result;
    }

    private void siftUp(int[] heap, int pos) {
        while (pos > 0) {
            int parent = (pos - 1) / 2;
            if (heap[parent*3+2] <= heap[pos*3+2]) break;
            swap(heap, parent, pos);
            pos = parent;
        }
    }

    private void siftDown(int[] heap, int size, int pos) {
        while (true) {
            int min = pos;
            int left = 2 * pos + 1;
            int right = 2 * pos + 2;

            if (left < size && heap[left*3+2] < heap[min*3+2]) min = left;
            if (right < size && heap[right*3+2] < heap[min*3+2]) min = right;

            if (min == pos) break;
            swap(heap, pos, min);
            pos = min;
        }
    }

    private void swap(int[] heap, int i, int j) {
        int temp = heap[i*3];
        heap[i*3] = heap[j*3];
        heap[j*3] = temp;

        temp = heap[i*3+1];
        heap[i*3+1] = heap[j*3+1];
        heap[j*3+1] = temp;

        temp = heap[i*3+2];
        heap[i*3+2] = heap[j*3+2];
        heap[j*3+2] = temp;
    }
}