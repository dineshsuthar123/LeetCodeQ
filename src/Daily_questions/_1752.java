package Daily_questions;

public class _1752 {
    static class ArrayCheck {
        public boolean check(int[] nums) {
            int count = 0, n = nums.length;
            for (int i = 0; i < n; i++) {
                if (nums[i] > nums[(i + 1) % n]) {
                    count++;
                }
            }
            return count <= 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 4, 5, 1, 2};
        ArrayCheck obj = new ArrayCheck();
        System.out.println(obj.check(nums));
    }
}
