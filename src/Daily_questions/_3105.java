package Daily_questions;

import java.util.*;

public class _3105 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        LongestMonotonicSubarray solver = new LongestMonotonicSubarray();
        System.out.println(solver.longestMonotonicSubarray(nums));
    }
}

class LongestMonotonicSubarray {
    public int longestMonotonicSubarray(int[] nums) {
        int inc = 1, dec = 1, max = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                inc++;
                dec = 1;
            } else if (nums[i] < nums[i - 1]) {
                dec++;
                inc = 1;
            } else {
                inc = dec = 1;
            }
            max = Math.max(max, Math.max(inc, dec));
        }
        return max;
    }
}
