package Daily_questions;

public class _2425 {
    static class Solution1 {
        public int xorAllNums(int[] nums1, int[] nums2) {
            int result = 0;
            if (nums2.length % 2 == 1) {
                for (int num : nums1) {
                    result ^= num;
                }
            }
            if (nums1.length % 2 == 1) {
                for (int num : nums2) {
                    result ^= num;
                }
            }
            return result;
        }
    }

    static class Solution2 {
        public int xorAllNums(int[] nums1, int[] nums2) {
            int c1 = nums1.length;
            int c2 = nums2.length;
            int x1 = 0, x2 = 0;
            if (c1 % 2 != 0) {
                for (int num : nums2) {
                    x2 ^= num;
                }
            }
            if (c2 % 2 != 0) {
                for (int num : nums1) {
                    x1 ^= num;
                }
            }
            return x1 ^ x2;
        }
    }

    public static void main(String[] args) {
        Solution1 sol1 = new Solution1();
        Solution2 sol2 = new Solution2();
        int[] nums1 = {2, 1, 3};
        int[] nums2 = {10, 2, 5, 0};
        System.out.println("Solution1 result: " + sol1.xorAllNums(nums1, nums2));
        System.out.println("Solution2 result: " + sol2.xorAllNums(nums1, nums2));
    }
}