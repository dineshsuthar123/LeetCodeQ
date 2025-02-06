package Daily_questions;

import java.util.*;

public class _1726 {
    static class Solution1 {
        public int tupleSameProduct(int[] nums) {
            Map<Integer, Integer> productCount = new HashMap<>();
            int n = nums.length;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int product = nums[i] * nums[j];
                    productCount.put(product, productCount.getOrDefault(product, 0) + 1);
                }
            }

            int total = 0;
            for (int count : productCount.values()) {
                if (count >= 2) {
                    total += count * (count - 1) / 2;
                }
            }

            return total * 8;
        }
    }

    static class Solution2 {
        public int tupleSameProduct(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int result = 0;
            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    int product = nums[i] * nums[j];
                    result += 8 * map.getOrDefault(product, 0);
                    map.put(product, map.getOrDefault(product, 0) + 1);
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 4, 6};
        Solution1 sol1 = new Solution1();
        Solution2 sol2 = new Solution2();

        System.out.println("Solution1 Output: " + sol1.tupleSameProduct(nums));
        System.out.println("Solution2 Output: " + sol2.tupleSameProduct(nums));
    }
}
