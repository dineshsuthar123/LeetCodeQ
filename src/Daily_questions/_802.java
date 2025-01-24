package Daily_questions;

import java.util.*;

public class _802 {
    public static void main(String[] args) {
        SafeNodesResolver solver = new SafeNodesResolver();

        int[][] graph1 = {{1,2},{2,3},{5},{0},{5},{},{}};
        System.out.println("Test Case 1: " + solver.eventualSafeNodes(graph1));

        int[][] graph2 = {{1,2,3,4},{1,2},{3,4},{0,4},{}};
        System.out.println("Test Case 2: " + solver.eventualSafeNodes(graph2));
    }
}

class SafeNodesResolver {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int [] state = new int[n];
        List<Integer> safe = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(dfs(graph, i, state)){
                safe.add(i);
            }
        }
        return safe;
    }
    private boolean dfs(int [][] graph, int node, int [] state){
        if(state[node] > 0) return state[node] == 2;
        state[node] = 1;

        for(int next : graph[node]){
            if(state[next] == 1 || !dfs(graph, next, state)){
                return false;
            }
        }
        state[node] = 2;
        return true;
    }
}