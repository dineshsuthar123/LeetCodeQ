package Daily_questions;

public class _1267 {
    static class ServerCommunicationSolution1 {
        public int countServers(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[] rowCount = new int[m];
            int[] colCount = new int[n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rowCount[i]++;
                        colCount[j]++;
                    }
                }
            }

            int communicatingServers = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && (rowCount[i] > 1 || colCount[j] > 1)) {
                        communicatingServers++;
                    }
                }
            }

            return communicatingServers;
        }
    }

    static class ServerCommunicationSolution2 {
        public int countServers(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;

            int[] rowServers = new int[m];
            int[] colServers = new int[n];

            int totalServers = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1) {
                        rowServers[i]++;
                        colServers[j]++;
                        totalServers++;
                    }
                }
            }

            int communicatingServers = totalServers;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 1 && rowServers[i] == 1 && colServers[j] == 1) {
                        communicatingServers--;
                    }
                }
            }

            return communicatingServers;
        }
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1,0},{0,1}};
        int[][] grid2 = {{1,0},{1,1}};
        int[][] grid3 = {{1,1,0,0},{0,0,1,0},{0,0,1,0},{0,0,0,1}};

        ServerCommunicationSolution1 solution1 = new ServerCommunicationSolution1();
        ServerCommunicationSolution2 solution2 = new ServerCommunicationSolution2();

        System.out.println("Solution 1 Results:");
        System.out.println("Grid 1: " + solution1.countServers(grid1));
        System.out.println("Grid 2: " + solution1.countServers(grid2));
        System.out.println("Grid 3: " + solution1.countServers(grid3));

        System.out.println("\nSolution 2 Results:");
        System.out.println("Grid 1: " + solution2.countServers(grid1));
        System.out.println("Grid 2: " + solution2.countServers(grid2));
        System.out.println("Grid 3: " + solution2.countServers(grid3));
    }
}