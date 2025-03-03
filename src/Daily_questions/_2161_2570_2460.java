package Daily_questions;

import java.util.*;

class _2161_2570_2460 {
    // 2460. Apply Operations to an Array
    static class Solution2460 {
        public int[] applyOperations(int[] nums) {
            int n = nums.length;
            int[] result = new int[n];
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] == nums[i + 1]) {
                    nums[i] *= 2;
                    nums[i + 1] = 0;
                }
            }
            for (int i = 0, j = 0; i < n; i++) {
                if (nums[i] != 0) {
                    result[j] = nums[i];
                    j++;
                }
            }
            return result;
        }
    }

    // 2570. Merge Two 2D Arrays by Summing Values
    static class Solution2570 {
        public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
            int i = 0, j = 0;
            List<int[]> resultList = new ArrayList<>();

            while (i < nums1.length && j < nums2.length) {
                int id1 = nums1[i][0], val1 = nums1[i][1];
                int id2 = nums2[j][0], val2 = nums2[j][1];

                if (id1 < id2) {
                    resultList.add(new int[]{id1, val1});
                    i++;
                } else if (id2 < id1) {
                    resultList.add(new int[]{id2, val2});
                    j++;
                } else {
                    resultList.add(new int[]{id1, val1 + val2});
                    i++;
                    j++;
                }
            }

            while (i < nums1.length) resultList.add(nums1[i++]);
            while (j < nums2.length) resultList.add(nums2[j++]);

            return resultList.toArray(new int[resultList.size()][]);
        }
    }

    // 2161. Partition Array According to Given Pivot
    static class Solution2161 {
        public int[] pivotArray(int[] nums, int pivot) {
            int[] result = new int[nums.length];
            int l = 0, r = nums.length - 1;

            for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
                if (nums[i] < pivot) result[l++] = nums[i];
                if (nums[j] > pivot) result[r--] = nums[j];
            }

            while (l <= r) result[l++] = pivot;
            return result;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Testing 2460
        Solution2460 sol2460 = new Solution2460();
        int[] nums1 = {1, 2, 2, 1, 1, 0};
        System.out.println("Apply Operations (2460): " + Arrays.toString(sol2460.applyOperations(nums1)));

        // Testing 2570
        Solution2570 sol2570 = new Solution2570();
        int[][] nums2_1 = {{1, 2}, {2, 3}, {3, 4}};
        int[][] nums2_2 = {{2, 4}, {3, 6}, {5, 7}};
        System.out.println("Merge Arrays (2570): " + Arrays.deepToString(sol2570.mergeArrays(nums2_1, nums2_2)));

        // Testing 2161
        Solution2161 sol2161 = new Solution2161();
        int[] nums3 = {9, 12, 5, 10, 14, 3, 10};
        int pivot = 10;
        System.out.println("Pivot Array (2161): " + Arrays.toString(sol2161.pivotArray(nums3, pivot)));
    }
}
