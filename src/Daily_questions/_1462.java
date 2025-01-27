package Daily_questions;

import java.util.*;

public class _1462 {
    public static void main(String[] args) {

        Object[][] testCases = {
                {2, new int[][]{{1, 0}}, new int[][]{{0, 1}, {1, 0}}},
                {2, new int[][]{}, new int[][]{{1, 0}, {0, 1}}},
                {3, new int[][]{{1, 2}, {1, 0}, {2, 0}}, new int[][]{{1, 0}, {1, 2}}}
        };

        FloydWarshallSolution fwSolution = new FloydWarshallSolution();
        TopologicalSortSolution tsSolution = new TopologicalSortSolution();

        for (int i = 0; i < testCases.length; i++) {
            int numCourses = (int) testCases[i][0];
            int[][] prerequisites = (int[][]) testCases[i][1];
            int[][] queries = (int[][]) testCases[i][2];

            System.out.println("\nTest Case " + (i + 1) + ":");
            System.out.println("numCourses: " + numCourses);
            System.out.println("prerequisites: " + Arrays.deepToString(prerequisites));
            System.out.println("queries: " + Arrays.deepToString(queries));

            List<Boolean> fwResult = fwSolution.checkIfPrerequisite(numCourses, prerequisites, queries);
            List<Boolean> tsResult = tsSolution.checkIfPrerequisite(numCourses, prerequisites, queries);

            System.out.println("Floyd-Warshall Solution Result: " + fwResult);
            System.out.println("Topological Sort Solution Result: " + tsResult);
            System.out.println("Both solutions match: " + fwResult.equals(tsResult));
        }
    }
}

class FloydWarshallSolution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        boolean[][] graph = new boolean[numCourses][numCourses];

        for (int[] prereq : prerequisites) {
            graph[prereq[0]][prereq[1]] = true;
        }

        for (int k = 0; k < numCourses; k++) {
            for (int i = 0; i < numCourses; i++) {
                for (int j = 0; j < numCourses; j++) {
                    graph[i][j] = graph[i][j] || (graph[i][k] && graph[k][j]);
                }
            }
        }

        List<Boolean> answer = new ArrayList<>();
        for (int[] query : queries) {
            answer.add(graph[query[0]][query[1]]);
        }

        return answer;
    }
}

class TopologicalSortSolution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Integer>[] adj = new ArrayList[numCourses];
        boolean[][] prereq = new boolean[numCourses][numCourses];
        int[] inDegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : prerequisites) {
            int a = edge[0], b = edge[1];
            adj[a].add(b);
            prereq[b][a] = true;
            inDegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                for (int i = 0; i < numCourses; i++) {
                    if (prereq[u][i]) prereq[v][i] = true;
                }
                if (--inDegree[v] == 0) q.add(v);
            }
        }

        List<Boolean> ans = new ArrayList<>();
        for (int[] query : queries) {
            int u = query[0], v = query[1];
            ans.add(prereq[v][u]);
        }
        return ans;
    }
}
