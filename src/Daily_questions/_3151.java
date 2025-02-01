package Daily_questions;

import java.util.Scanner;

class SpecialArrayChecker {
    public boolean isArraySpecial(int[] nums) {
        boolean ans = true;
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] % 2 == nums[i - 1] % 2) {
                return false;
            }
        }
        return ans;
    }
}

public class _3151 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter array size: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        SpecialArrayChecker checker = new SpecialArrayChecker();
        System.out.println("Is the array special? " + checker.isArraySpecial(nums));
    }
}
