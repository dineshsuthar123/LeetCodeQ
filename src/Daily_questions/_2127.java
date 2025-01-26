package Daily_questions;

import java.util.*;

class MaximumEmployeesMeeting {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;
        int[] inDeg = new int[n];
        int[] chainLen = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        for (int f : favorite) inDeg[f]++;
        for (int i = 0; i < n; i++) if (inDeg[i] == 0) q.add(i);

        while (!q.isEmpty()) {
            int u = q.poll();
            visited[u] = true;
            int v = favorite[u];
            chainLen[v] = Math.max(chainLen[v], chainLen[u] + 1);
            if (--inDeg[v] == 0) q.add(v);
        }

        int maxCycle = 0, pairChains = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int cycleLen = 0, current = i;
            while (!visited[current]) {
                visited[current] = true;
                current = favorite[current];
                cycleLen++;
            }
            if (cycleLen == 2)
                pairChains += 2 + chainLen[i] + chainLen[favorite[i]];
            else maxCycle = Math.max(maxCycle, cycleLen);
        }
        return Math.max(maxCycle, pairChains);
    }

    public static void main(String[] args) {
        MaximumEmployeesMeeting solution = new MaximumEmployeesMeeting();

        int[] favorite1 = {2,2,1,2};
        System.out.println("Test Case 1: " + solution.maximumInvitations(favorite1));

        int[] favorite2 = {1,2,0};
        System.out.println("Test Case 2: " + solution.maximumInvitations(favorite2));

        int[] favorite3 = {3,0,1,4,1};
        System.out.println("Test Case 3: " + solution.maximumInvitations(favorite3));
    }
}