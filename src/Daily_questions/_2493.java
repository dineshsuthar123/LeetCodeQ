package Daily_questions;

// File: _2493.java

import java.util.*;

class Solution12493 {
    List<List<Integer>> graph;
    int n;
    boolean[] visited;
    int[] color;

    public int magnificentSets(int n, int[][] edges) {
        this.n = n;
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        visited = new boolean[n + 1];
        color = new int[n + 1];

        int totalGroups = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    component.add(curr);
                    for (int neighbor : graph.get(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }

                if (!isBipartite(component.get(0))) {
                    return -1;
                }

                int maxDepth = 0;
                for (int node : component) {
                    maxDepth = Math.max(maxDepth, bfsMaxDepth(node));
                }

                totalGroups += maxDepth;
            }
        }

        return totalGroups;
    }

    private boolean isBipartite(int node) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);
        color[node] = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph.get(curr)) {
                if (color[neighbor] == 0) {
                    color[neighbor] = -color[curr];
                    queue.add(neighbor);
                } else if (color[neighbor] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int bfsMaxDepth(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        boolean[] vis = new boolean[n + 1];
        vis[start] = true;
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                for (int neighbor : graph.get(curr)) {
                    if (!vis[neighbor]) {
                        vis[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            depth++;
        }
        return depth;
    }
}

class Solution22493 {
    private int[] color;
    private List<List<Integer>> adj;
    private int n;

    private boolean isBipartite(int node, int c, List<Integer> component) {
        color[node] = c;
        component.add(node);
        for (int nbr : adj.get(node)) {
            if (color[nbr] == c) return false;
            if (color[nbr] == -1 && !isBipartite(nbr, 1 - c, component)) return false;
        }
        return true;
    }

    private int maxGroupsInComponent(List<Integer> component) {
        int maxDepth = 0;
        for (int start : component) {
            int[] depth = new int[n];
            Arrays.fill(depth, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            depth[start] = 0;
            while (!q.isEmpty()) {
                int node = q.poll();
                for (int nbr : adj.get(node)) {
                    if (depth[nbr] == -1) {
                        depth[nbr] = depth[node] + 1;
                        maxDepth = Math.max(maxDepth, depth[nbr]);
                        q.add(nbr);
                    }
                }
            }
        }
        return maxDepth + 1;
    }

    public int magnificentSets(int n, int[][] edges) {
        this.n = n;
        color = new int[n];
        Arrays.fill(color, -1);
        adj = new ArrayList<>();
        for(int i = 0; i < n; i++){
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            int u = edge[0] - 1, v = edge[1] - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        List<List<Integer>> components = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(color[i] == -1){
                List<Integer> component = new ArrayList<>();
                if(!isBipartite(i, 0, component)) return -1;
                components.add(component);
            }
        }

        int total = 0;
        for(List<Integer> comp : components){
            total += maxGroupsInComponent(comp);
        }
        return total;
    }
}

public class _2493 {
    public static void main(String[] args) {
        int n1 = 6;
        int[][] edges1 = {{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}};
        Solution12493 sol1 = new Solution12493();
        Solution22493 sol2 = new Solution22493();

        System.out.println("Solution1 Test Case 1: " + sol1.magnificentSets(n1, edges1));
        System.out.println("Solution2 Test Case 1: " + sol2.magnificentSets(n1, edges1));

        int n2 = 3;
        int[][] edges2 = {{1,2},{2,3},{3,1}};
        System.out.println("Solution1 Test Case 2: " + sol1.magnificentSets(n2, edges2));
        System.out.println("Solution2 Test Case 2: " + sol2.magnificentSets(n2, edges2));
    }
}