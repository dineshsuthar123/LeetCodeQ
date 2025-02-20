package Daily_questions;

public class _1980 {
    public static void main(String[] args) {
        String[] nums = {"01", "10"};
        Solution_1980 sol = new Solution_1980();
        System.out.println(sol.findDifferentBinaryString(nums)); // Output: "11" or "00"
    }
}

class Solution_1980 {
    public String findDifferentBinaryString(String[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }
        return sb.toString();
    }
}
