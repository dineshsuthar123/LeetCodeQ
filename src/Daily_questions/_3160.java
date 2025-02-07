package Daily_questions;

import java.util.*;

public class _3160 {
    public static void main(String[] args) {
        int limit = 5;
        int[][] queries = {{1, 2}, {2, 3}, {1, 3}, {4, 2}, {2, 2}};

        Solution1_3160 sol1 = new Solution1_3160();
        System.out.println("Solution1 Output: " + Arrays.toString(sol1.queryResults(limit, queries)));

        Solution2_3160 sol2 = new Solution2_3160();
        System.out.println("Solution2 Output: " + Arrays.toString(sol2.queryResults(limit, queries)));
    }
}

class Solution1_3160 {
    public int[] queryResults(int limit, int[][] queries) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> ballColor = new HashMap<>();
        Map<Integer, Integer> colorCount = new HashMap<>();
        int currentDistinct = 0;

        for (int[] query : queries) {
            int x = query[0];
            int y = query[1];

            if (ballColor.containsKey(x)) {
                int oldColor = ballColor.get(x);
                int cnt = colorCount.get(oldColor);
                cnt--;
                if (cnt == 0) {
                    colorCount.remove(oldColor);
                    currentDistinct--;
                } else {
                    colorCount.put(oldColor, cnt);
                }
            }

            ballColor.put(x, y);

            if (colorCount.containsKey(y)) {
                colorCount.put(y, colorCount.get(y) + 1);
            } else {
                colorCount.put(y, 1);
                currentDistinct++;
            }

            result.add(currentDistinct);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}

class Solution2_3160 {
    public int[] queryResults(int limit, int[][] queries) {
        Map<Integer, Integer> ball = new HashMap<>(), color = new HashMap<>();
        int n = queries.length, distinct = 0;
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int pos = queries[i][0], c = queries[i][1];
            if (ball.containsKey(pos)) {
                int cnt = color.get(ball.get(pos)) - 1;
                if (cnt == 0) {
                    color.remove(ball.get(pos));
                    distinct--;
                } else {
                    color.put(ball.get(pos), cnt);
                }
            }
            ball.put(pos, c);
            int cnt = color.getOrDefault(c, 0) + 1;
            color.put(c, cnt);
            if (cnt == 1) distinct++;
            ans[i] = distinct;
        }
        return ans;
    }
}
