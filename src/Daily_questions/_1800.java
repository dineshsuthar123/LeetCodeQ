package Daily_questions;

class _1800 {
    public static void main(String[] args) {
        int[] nums = {10, 20, 30, 5, 10, 50};
        System.out.println(new AscendingSum1().maxAscendingSum(nums));
        System.out.println(new AscendingSum2().maxAscendingSum(nums));
    }
}

class AscendingSum1 {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int max = 0;
        int curr = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                curr += nums[i];
            } else {
                max = Math.max(max, curr);
                curr = nums[i];
            }
        }
        return Math.max(curr, max);
    }
}

class AscendingSum2 {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int curr = nums[0], ans = nums[0];
        for (int i = 1; i < n; i++) {
            curr = nums[i] > nums[i - 1] ? curr + nums[i] : nums[i];
            ans = Math.max(curr, ans);
        }
        return ans;
    }
}
