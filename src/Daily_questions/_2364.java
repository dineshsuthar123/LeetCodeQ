package Daily_questions;

import java.util.*;

class UniqueSolution2364_1 {
    public long countBadPairs(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int key = nums[i] - i;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long sumGood = 0;
        for (int count : map.values()) {
            sumGood += (long) count * (count - 1) / 2;
        }
        long total = (long) n * (n - 1) / 2;
        return total - sumGood;
    }
}

class UniqueSolution2364_2 {
    public long countBadPairs(int[] nums) {
        Map<Integer , Integer> map = new HashMap<>();
        long count = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++){
            count+= map.merge(nums[i] - i, 1, Integer::sum) -1;
        }
        return 1L * (n - 1) * n / 2 - count;
    }
}

public class _2364 {
    public static void main(String[] args) {
        int[] nums = {4, 1, 3, 3};

        UniqueSolution2364_1 sol1 = new UniqueSolution2364_1();
        System.out.println("Solution 1: " + sol1.countBadPairs(nums));

        UniqueSolution2364_2 sol2 = new UniqueSolution2364_2();
        System.out.println("Solution 2: " + sol2.countBadPairs(nums));
    }
}
