package Daily_questions;

import java.util.*;

public class _2948 {
    public static void main(String[] args) {
        FirstSolution firstSolution = new FirstSolution();
        SecondSolution secondSolution = new SecondSolution();

        int[] nums1 = {1,5,3,2,8,9,4};
        int limit1 = 2;

        int[] result1 = firstSolution.lexicographicallySmallestArray(nums1, limit1);
        int[] result2 = secondSolution.lexicographicallySmallestArray(nums1, limit1);

        System.out.println("First Solution Result: " + Arrays.toString(result1));
        System.out.println("Second Solution Result: " + Arrays.toString(result2));
    }

    static class FirstSolution {
        public int[] lexicographicallySmallestArray(int[] nums, int limit) {
            int n = nums.length;

            class Pair {
                final int index, value;
                Pair(int index, int value) {
                    this.index = index;
                    this.value = value;
                }
            }

            Pair[] pairs = new Pair[n];
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                pairs[i] = new Pair(i, nums[i]);
            }

            Arrays.sort(pairs, (a, b) -> a.value - b.value);

            Pair[] ids = Arrays.copyOf(pairs, n);

            int i = 0;
            while (i < n) {
                int j = i;
                i++;

                while (i < n && pairs[i].value - pairs[i - 1].value <= limit) {
                    i++;
                }

                Arrays.sort(ids, j, i, (a, b) -> a.index - b.index);

                for (int k = j; k < i; k++) {
                    ans[ids[k].index] = pairs[k].value;
                }
            }

            return ans;
        }
    }

    static class SecondSolution {
        public int[] lexicographicallySmallestArray(int[] nums, int limit) {
            int n = nums.length;
            if(n == 0) return new int[0];
            int [][] sort = new int[n][2];
            for(int i = 0; i < n; i++){
                sort[i][0] = nums[i];
                sort[i][1] = i;
            }
            Arrays.sort(sort, (a , b) ->Integer.compare(a[0], b[0]));
            int []result = new int[n];
            int group = 0;
            for(int i = 0; i < n; i++){
                if(i == n - 1 || sort[i+1][0] - sort[i][0] > limit){
                    List<Integer> indices = new ArrayList<>();
                    for(int j = group; j <= i; j++) indices.add(sort[j][1]);
                    Collections.sort(indices);
                    for(int j = 0; j < indices.size(); j++) result[indices.get(j)] = sort[group + j][0];
                    group = i + 1;
                }
            }
            return result;
        }
    }
}