package Daily_questions;

class _1749_873 {
    static class Solution1749 {
        public int maxAbsoluteSum(int[] nums) {
            int maxEndingHere = 0, minEndingHere = 0, maxSoFar = 0, minSoFar = 0;
            for (int num : nums) {
                maxEndingHere = Math.max(num, maxEndingHere + num);
                minEndingHere = Math.min(num, minEndingHere + num);
                maxSoFar = Math.max(maxSoFar, maxEndingHere);
                minSoFar = Math.min(minSoFar, minEndingHere);
            }
            return Math.max(Math.abs(maxSoFar), Math.abs(minSoFar));
        }
    }

    static class Solution873 {
        public int lenLongestFibSubseq(int[] arr) {
            int n = arr.length, maxLen = 0;
            int[][] dp = new int[n][n];
            for (int curr = 2; curr < n; curr++) {
                int start = 0, end = curr - 1;
                while (start < end) {
                    int pairSum = arr[start] + arr[end];
                    if (pairSum > arr[curr]) end--;
                    else if (pairSum < arr[curr]) start++;
                    else {
                        dp[end][curr] = dp[start][end] + 1;
                        maxLen = Math.max(dp[end][curr], maxLen);
                        end--;
                        start++;
                    }
                }
            }
            return maxLen == 0 ? 0 : maxLen + 2;
        }
    }

    public static void main(String[] args) {

        int[] nums1 = {1, -3, 2, 3, -4};
        int[] nums2 = {2, -5, 1, -4, 3, -2};
        Solution1749 sol1749 = new Solution1749();
        System.out.println("Max Absolute Sum (1749): " + sol1749.maxAbsoluteSum(nums1)); // Output: 5
        System.out.println("Max Absolute Sum (1749): " + sol1749.maxAbsoluteSum(nums2)); // Output: 8

        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] arr2 = {1, 3, 7, 11, 12, 14, 18};
        Solution873 sol873 = new Solution873();
        System.out.println("Longest Fibonacci-like Subsequence (873): " + sol873.lenLongestFibSubseq(arr1)); // Output: 5
        System.out.println("Longest Fibonacci-like Subsequence (873): " + sol873.lenLongestFibSubseq(arr2)); // Output: 3
    }
}
