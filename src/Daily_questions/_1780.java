package Daily_questions;

public class _1780 {
    public static void main(String[] args) {
        Solution1780 solution = new Solution1780();

        int[] testCases = {12, 91, 21};
        for (int n : testCases) {
            System.out.println("Input: " + n + " -> Output: " + solution.checkPowersOfThree(n));
        }
    }
}

class Solution1780 {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
