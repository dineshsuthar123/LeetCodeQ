package Daily_questions;

public class _684 {
    public static void main(String[] args) {
        // Test cases
        int[][] edges1 = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        int[][] edges2 = {{1,2}, {1,3}, {2,3}};

        RedundantConnectionBasic solution1 = new RedundantConnectionBasic();
        RedundantConnectionWithRank solution2 = new RedundantConnectionWithRank();

        // Test both implementations
        System.out.println("Testing Basic Implementation:");
        printResult(edges1, solution1.findRedundantConnection(edges1));
        printResult(edges2, solution1.findRedundantConnection(edges2));

        System.out.println("\nTesting Rank Implementation:");
        printResult(edges1, solution2.findRedundantConnection(edges1));
        printResult(edges2, solution2.findRedundantConnection(edges2));
    }

    private static void printResult(int[][] edges, int[] result) {
        System.out.print("Input edges: ");
        for (int[] edge : edges) {
            System.out.print("[" + edge[0] + "," + edge[1] + "] ");
        }
        System.out.println("\nRedundant edge: [" + result[0] + "," + result[1] + "]");
    }
}

class RedundantConnectionBasic {
    private int parent[];

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int edge[] : edges) {
            if (!union(edge[0], edge[1])) {
                return edge;
            }
        }
        return new int[0];
    }

    private int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    private boolean union(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if (pu == pv) return false;
        parent[pu] = pv;
        return true;
    }
}

class RedundantConnectionWithRank {
    private int[] parent;
    private int[] rank;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (find(u) == find(v)) {
                return edge;
            }
            join(u, v);
        }
        return new int[0];
    }

    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    private void join(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}